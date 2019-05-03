package com.justinrmiller.playground

import arrow.core.*

open class GeneralException: Exception()

class ConnectionFailedException: GeneralException()

class AuthorizationException: GeneralException()

fun checkPermissions(hashedPass: String): Try<Unit> {
    return Try {
        if (hashedPass != "12345") {
            throw AuthorizationException()
        }
        // do more validation logic here, if you wish
    }
}

fun getLotteryNumbersFromCache(): Try<List<Int>> {
    //return Try { listOf(1, 2, 3, 4) }

    // note: the following will intentionally fail the connection if uncommented
    return Try {
        throw ConnectionFailedException()
    }
}

fun getLotteryNumbersFromCloud(): Try<List<Int>> {
    // note: the following will intentionally fail the connection if uncommented
    // return Try {
    //    throw ConnectionFailedException()
    // }

    return Try { listOf(1, 2, 3, 4) }
}

enum class Source {
    CACHE, NETWORK
}

fun getLotteryNumbers(source: Source): Try<List<Int>> {
    return when(source) {
        Source.CACHE -> getLotteryNumbersFromCache()
        Source.NETWORK -> getLotteryNumbersFromCloud()
    }
}

fun getLotteryNumbers(hashedPass: String): Try<List<Int>> {
    return when(val permissionCheck = checkPermissions(hashedPass)) {
        is Success -> getLotteryNumbers(Source.CACHE).handleErrorWith { getLotteryNumbers(Source.NETWORK) }
        is Failure -> permissionCheck
    }
}

fun main() {

    val lotteryTry = getLotteryNumbers("12345")

    println("Lotto Number Try: $lotteryTry")

    when (val numbers = getLotteryNumbers("12345")) {
        is Success -> println(numbers.value)
        is Failure -> println("Unable to retrieve lottery numbers")
    }

}
