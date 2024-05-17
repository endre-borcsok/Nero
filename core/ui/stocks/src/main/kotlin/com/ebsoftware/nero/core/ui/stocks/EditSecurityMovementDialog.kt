package com.ebsoftware.nero.core.ui.stocks

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData

internal object EditSecurityMovementDialogParameters {
    @StringRes val dialogTitle = R.string.security_movement_edit_dialog_title

    @StringRes val dialogEditTicker = R.string.security_movement_edit_dialog_ticker

    @StringRes val dialogCancelText = R.string.security_movement_edit_dialog_cancel

    @StringRes val dialogConfirmText = R.string.security_movement_edit_dialog_confirm

    val contentPadding = 16.dp
    val verticalSpacing = 16.dp
}

@Composable
fun EditSecurityMovementDialog(
    securityMovementViewData: SecurityMovementViewData,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onUpdate: (SecurityMovementViewData) -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            modifier = modifier,
        ) {
            Column(
                modifier = Modifier.padding(EditSecurityMovementDialogParameters.contentPadding),
                verticalArrangement = Arrangement.spacedBy(EditSecurityMovementDialogParameters.verticalSpacing),
            ) {
                var tickerEditText by rememberSaveable { mutableStateOf(securityMovementViewData.ticker) }
                Text(
                    text = stringResource(id = EditSecurityMovementDialogParameters.dialogTitle),
                )
                OutlinedTextField(
                    value = tickerEditText,
                    onValueChange = { tickerEditText = it },
                    label = {
                        Text(text = stringResource(id = EditSecurityMovementDialogParameters.dialogEditTicker))
                    },
                )
                ActionButtons(
                    modifier = Modifier.fillMaxWidth(),
                    onCancel = onDismiss,
                    onConfirm = {
                        onUpdate(securityMovementViewData.copy(ticker = tickerEditText))
                    },
                )
            }
        }
    }
}

@Composable
private fun ActionButtons(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(
            onClick = onCancel,
        ) {
            Text(
                text = stringResource(id = EditSecurityMovementDialogParameters.dialogCancelText),
            )
        }
        Button(
            onClick = onConfirm,
        ) {
            Text(
                text = stringResource(id = EditSecurityMovementDialogParameters.dialogConfirmText),
            )
        }
    }
}

@Composable
@Preview
internal fun EditSecurityMovementDialogPreview() {
    EditSecurityMovementDialog(
        securityMovementViewData = SecurityMovementViewData.EMPTY,
        onDismiss = {},
        onUpdate = {},
    )
}
