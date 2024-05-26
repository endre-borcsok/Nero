package com.ebsoftware.nero.core.ui.stocks

import android.icu.text.DateFormat
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import java.util.Locale

internal object SecurityMovementParams {
    @StringRes val quantityText = R.string.security_movement_quantity_text
    val cardShape = RoundedCornerShape(16.dp)
    val contentPadding = 16.dp
    val verticalSpacing = 16.dp
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SecurityMovement(
    viewData: SecurityMovementViewData,
    modifier: Modifier = Modifier,
    onClick: (SecurityMovementViewData) -> Unit,
    onEditDetails: (SecurityMovementViewData) -> Unit,
) {
    Card(
        shape = SecurityMovementParams.cardShape,
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clip(SecurityMovementParams.cardShape)
            .combinedClickable(
                onClick = { onClick(viewData) },
                onLongClick = { onEditDetails(viewData) },
            ),
    ) {
        Row(
            modifier = Modifier.padding(SecurityMovementParams.contentPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(SecurityMovementParams.verticalSpacing),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = viewData.ticker,
                )
                Spacer(
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "${stringResource(SecurityMovementParams.quantityText)}: ${viewData.quantity}",
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(SecurityMovementParams.verticalSpacing),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = DateFormat.getInstance().format(viewData.date),
                )
                Spacer(
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = String.format(Locale.ENGLISH, "%.2f", viewData.cost),
                )
            }
        }
    }
}

@Composable
@Preview
internal fun SecurityMovementPreview() {
    SecurityMovement(
        viewData = SecurityMovementViewData.EMPTY.copy(
            ticker = "AAPL",
            quantity = 4,
            cost = 123.445,
        ),
        onClick = {},
        onEditDetails = {},
    )
}
