package com.rickyhu.hushkeyboard.utils

import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.VibratorManager

fun VibratorManager.maybeVibrate() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return

    val effectId = VibrationEffect.Composition.PRIMITIVE_CLICK

    this.vibrate(
        CombinedVibration.createParallel(
            VibrationEffect
                .startComposition()
                .addPrimitive(effectId)
                .compose(),
        ),
    )
}
