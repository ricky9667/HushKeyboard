package com.rickyhu.hushkeyboard.introduction

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme
import splitties.systemservices.inputMethodManager
import java.lang.Thread.sleep

@Composable
fun IntroductionScreen(
    navigateToHome: () -> Unit = {}
) {
    val context = LocalContext.current
    var isSection1Finished by remember { mutableStateOf(false) }
    var isSection2Finished by remember { mutableStateOf(false) }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(R.string.intro_screen_title),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(vertical = 32.dp)
                )

                Step1Section(
                    isSectionFinished = isSection1Finished,
                    onSectionButtonClicked = {
                        val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
                        context.startActivity(intent)
                        sleep(1000)
                        isSection1Finished = true
                    }
                )

                if (isSection1Finished) {
                    Step2Section(
                        isSectionFinished = isSection2Finished,
                        onSectionButtonClicked = {
                            inputMethodManager.showInputMethodPicker()
                            sleep(500)
                            isSection2Finished = true
                        }
                    )
                }

                if (isSection1Finished && isSection2Finished) {
                    Step3Section(
                        navigateToHome = navigateToHome
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun IntroductionScreenPreview() {
    HushKeyboardTheme {
        IntroductionScreen()
    }
}
