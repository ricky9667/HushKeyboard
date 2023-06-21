package com.rickyhu.hushkeyboard.model

enum class Turns(val value: Int) {
    Single(1),
    Double(2),
    Triple(3);

    override fun toString() = if (this == Single) "" else value.toString()
}
