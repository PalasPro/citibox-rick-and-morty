package com.palaspro.citiboxchallenge.domainlayer.usecase

import com.palaspro.citiboxchallenge.domainlayer.DomainLayerContract
import com.palaspro.citiboxchallenge.domainlayer.model.CharacterBo
import com.palaspro.citiboxchallenge.domainlayer.model.MatchStateBo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val BEST_MATCH_BEER_UC = "bestMatchBeerUc"

class BestMatchBeerUc(
    private val repositoryCharacter: DomainLayerContract.CharactersRepository,
    private val locationRepository: DomainLayerContract.LocationRepository,
    private val episodeRepository: DomainLayerContract.EpisodeRepository
) : DomainLayerContract.UseCaseFlow<Int, MatchStateBo> {

    override fun run(params: Int): Flow<MatchStateBo> = flow {
        emit(MatchStateBo.Searching)

        // get current character
        repositoryCharacter.getCharacterSync(params).orNull()?.let { character ->
            // get people in the current location
            locationRepository.getLocation(character.location.code).orNull()?.let { location ->
                // get characters in the same location
                val residents = location.residents.filter { it != character.id }
                repositoryCharacter.getMultipleCharacters(residents).orNull()?.let { characters ->
                    // get a ordered list with [countEpisodes, diffEpisodes, id]
                    val countEpisodesOrdered = getOrderedEpisodesInfo(characters, character)
                    if (countEpisodesOrdered.isNotEmpty()) {
                        val firstKey = countEpisodesOrdered.first()

                        val characterMatch = characters.firstOrNull { it.id == firstKey.id }
                        val firstDate: String? =
                            episodeRepository.getEpisode(firstKey.firstEpisode).orNull()?.airDate
                        val lastDate: String? =
                            episodeRepository.getEpisode(firstKey.lastEpisode).orNull()?.airDate
                        val count: Int = firstKey.count

                        emit(
                            MatchStateBo.MatchBeerBo(
                                characterBo = characterMatch,
                                locationBo = location,
                                firstDate = firstDate,
                                lastDate = lastDate,
                                episodesMatch = count
                            )
                        )
                    } else {
                        emit(MatchStateBo.NotFound)
                    }
                } ?: emit(MatchStateBo.NotFound)
            } ?: emit(MatchStateBo.NotFound)
        } ?: emit(MatchStateBo.NotFound)
    }

    private fun getOrderedEpisodesInfo(
        characters: List<CharacterBo>,
        character: CharacterBo
    ) = characters
        .mapNotNull { char ->
            // get match episodes
            val episodes = character.episode
                .filter { char.episode.contains(it) }
                .sorted()
            if (episodes.isNotEmpty()) {
                val last = episodes.last()
                val first = episodes.first()
                InfoEpisodes(
                    id = char.id,
                    count = episodes.size,
                    firstEpisode = first,
                    lastEpisode = last,
                    diffEpisodes = last - first
                )

            } else {
                null
            }
        }
        .sortedByDescending { it }


    private data class InfoEpisodes(
        val id: Int,
        val count: Int,
        val lastEpisode: Int,
        val firstEpisode: Int,
        val diffEpisodes: Int
    ) : Comparable<InfoEpisodes> {

        override fun compareTo(other: InfoEpisodes): Int =
            if (count == other.count && diffEpisodes == other.diffEpisodes) {
                other.id.compareTo(id)
            } else if (count == other.count && diffEpisodes != other.diffEpisodes) {
                diffEpisodes.compareTo(other.diffEpisodes)
            } else {
                count.compareTo(other.count)
            }

    }
}