package com.rickyhu.hushkeyboard.introduction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

@Composable
fun Step1Section(
    modifier: Modifier = Modifier,
    isSectionFinished: Boolean,
    onSectionButtonClicked: () -> Unit = {}
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.intro_step_1_title),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.intro_step_1_description),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (isSectionFinished) {
            OutlinedButton(
                onClick = onSectionButtonClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("EnableKeyboardButtonOutlined")
            ) {
                Text(text = stringResource(R.string.intro_enable_keyboard_button))
            }
        } else {
            Button(
                onClick = onSectionButtonClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("EnableKeyboardButtonFilled")
            ) {
                Text(text = stringResource(R.string.intro_enable_keyboard_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Step1SectionPreview() {
    HushKeyboardTheme {
        Step1Section(
            isSectionFinished = false
        )
    }
}
