package com.justinrmiller.playground

data class User(val id: Int) {
    companion object
}

fun main() {

    val user1 = User(1)
    val user2 = User(2)

    println(user1)
    println(user2)

}
