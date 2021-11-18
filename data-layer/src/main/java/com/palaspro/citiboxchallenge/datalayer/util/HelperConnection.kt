package com.palaspro.citiboxchallenge.datalayer.util

inline fun <T> safeApiCall(responseFunction: () -> T): T? =
    try {
        responseFunction.invoke()//Or responseFunction()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }