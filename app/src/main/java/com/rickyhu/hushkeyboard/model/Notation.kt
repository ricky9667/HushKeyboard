package com.rickyhu.hushkeyboard.model

enum class Notation(val value: String) {
    R("R"),
    U("U"),
    F("F"),
    L("L"),
    D("D"),
    B("B"),
    M("M"),
    E("E"),
    S("S"),
    X("x"),
    Y("y"),
    Z("z");

    companion object {
        fun getCharList(): List<Char> = Notation.entries.map { it.value.single() }
    }
}
