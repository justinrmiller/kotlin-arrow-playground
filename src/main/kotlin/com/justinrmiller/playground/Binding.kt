package com.justinrmiller.playground

import arrow.core.*
import arrow.core.extensions.option.monad.binding

fun main() {

    val result1 = binding {
        val (a) = Some(1)
        val (b) = Some(1 + a)
        val (c) = Some(1 + b)
        a + b + c
    }

    println("Result1: $result1")

    val result2 = binding {
        val (x) = none<Int>()
        val (y) = Some(1 + x)
        val (z) = Some(1 + y)
        x + y + z
    }

    println("Result2: $result2")


}
