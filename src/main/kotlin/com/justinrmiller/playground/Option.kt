package com.justinrmiller.playground

import arrow.core.*

fun main() {

    val someValue: Option<String> = Some("A Provided Optional Value")
    val none: Option<String> = None

    val values = listOf(someValue, none)

    val transformedValues = values.map { value ->
        when (value) {
            is Some -> value.t
            is None -> "No value provided!"
        }
    }

    transformedValues.forEach { x -> println(x) }
}
