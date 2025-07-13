package com.rickyhu.hushkeyboard.introduction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.R
import com.rickyhu.hushkeyboard.theme.HushKeyboardTheme

private const val SEXY_MOVE_NOTATION = "R U R' U' "

@Composable
fun Step3Section(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {
    var text by remember { mutableStateOf("") }
    var openDoneDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.intro_step_3_title),
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.intro_step_3_description),
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                if (text == SEXY_MOVE_NOTATION) {
                    openDoneDialog = true
                }
            },
            label = { Text(text = "Type here") },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
            modifier = Modifier.fillMaxWidth(),
        )
    }

    if (openDoneDialog) {
        SetupDoneDialog(
            onDone = {
                openDoneDialog = false
                navigateToHome()
            },
        )
    }
}

@Composable
private fun SetupDoneDialog(onDone: () -> Unit) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Done",
            )
        },
        title = { Text(text = stringResource(R.string.intro_done_dialog_title)) },
        text = { Text(text = stringResource(R.string.intro_done_dialog_text)) },
        onDismissRequest = onDone,
        confirmButton = {
            TextButton(onClick = onDone) {
                Text("OK")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun Step3SectionPreview() {
    HushKeyboardTheme {
        Step3Section(
            navigateToHome = {},
        )
    }
}
