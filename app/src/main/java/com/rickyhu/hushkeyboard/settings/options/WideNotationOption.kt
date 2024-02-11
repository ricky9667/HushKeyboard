package com.rickyhu.hushkeyboard.settings.options

enum class WideNotationOption {
    WideWithW, WideWithLowercase;

    override fun toString() = when (this) {
        WideWithW -> "Use w (Rw)"
        WideWithLowercase -> "Use lowercase (r)"
    }
}
