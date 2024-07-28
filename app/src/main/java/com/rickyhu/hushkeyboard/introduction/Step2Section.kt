package com.rickyhu.hushkeyboard.introduction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme
import splitties.systemservices.inputMethodManager

@Composable
fun Step2Section(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.intro_step_2_title),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.intro_step_2_description),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { inputMethodManager.showInputMethodPicker() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.intro_select_input_method))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Step2SectionPreview() {
    HushKeyboardTheme {
        Step2Section()
    }
}
