# Citibox Mobile Tech Challenge

This android app has been development as a Citibox challenge. The application has the follow features:
1. A main screen where appear a character list of Rick And Morty program. Each item show the image and name of character. The user can filter the characters by name tipping in the text field.
2. A Matcher Beer screen. In this screen the app math the best colleague to take a beer selecting a character. The conditions to take a beer are the follow:
   - Characters can only meet actors who are in the same location.
   - So that they have enough anecdotes to tell while they get drunk, priority will be given to the characters who have shared the filming set the most times.
   - In the event that two possible *matches* have shared the same number of chapters with the selected character, those who have known each other for the longest time will be preferred.
   - If they have participated in the same number of chapters, and they met on the same day, priority will be given to the candidate who has not seen the selected character for the longest time.
   - In case there is more than one candidate that meets all the criteria, it will be sorted by ID.

## Architecture
The architecture is based in three layers. Each layer is a Gradle module.

#### 1. Domain layer
This layer has the logic of the behaviour that we want to do in the app. This layer implements the use cases and bridges to interact between data and presenter layer.
- Business Objects (Bo)
- Use cases: They have the business logic. They work with the repositories to get, set and transform the Bo.
- Bridge: A bridge can to have one or more use cases. It will work into a ViewModel

#### 2. Data source layer
Here is the necessary code to get the data from the API and store in the device. This layer persistence and manage the communication with The Rick And Morty API.
- Data Objects (Dto)
- Mappers to transform each type layer objects (Dto <-> Entity, Dto <-> Bo)
- DataSources: Local and Remote data sources for each data type (characters, locations, episodes)
- Implementation domain repositories

#### 3. Presenter layer
Where the user see the data.
- **MVVM** design pattern has been used.
- Each feature is a package with the structure:
  - *ui*: Jetckpack Compose is used in all this layer
  - *viewmodel*: It has the ViewModel classes. Each view model uses a Bridge that contains all use cases necessary for to work its UI.

## Technology stack
- Language: **Kotlin**
- Dependency injection: **Koin**
- Asynchronous computation: **Flow**
- Android Jetpack components:
  - **Androidx**
  - **Room**
  - **ViewModel**
  - **Compose**
  - **Navigation**
- Other relevant libraries:
  - Functional programming: **Arrow**
  - To load images: **Coin**
