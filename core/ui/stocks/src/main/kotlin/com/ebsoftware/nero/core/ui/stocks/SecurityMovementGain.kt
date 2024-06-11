package com.ebsoftware.nero.core.ui.stocks

import android.icu.text.DateFormat
import androidx.annotation.StringRes
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementGainViewData
import java.util.Locale

internal object SecurityMovementGainParams {
    @StringRes val costText = R.string.security_movement_cost_text
    val cardShape = RoundedCornerShape(16.dp)
    val contentPadding = 16.dp
    val verticalSpacing = 16.dp
}

@Composable
fun SecurityMovementGain(
    viewData: SecurityMovementGainViewData,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = SecurityMovementGainParams.cardShape,
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clip(SecurityMovementGainParams.cardShape),
    ) {
        Row(
            modifier = Modifier.padding(SecurityMovementGainParams.contentPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(SecurityMovementGainParams.verticalSpacing),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = viewData.ticker,
                )
                Spacer(
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "${stringResource(SecurityMovementGainParams.costText)}: ${viewData.cost}",
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(SecurityMovementGainParams.verticalSpacing),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "${String.format(Locale.ENGLISH, "%.2f", viewData.gainPercent)}%",
                    color = getGainTextColor(viewData.gainPercent),
                )
                Spacer(
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = String.format(Locale.ENGLISH, "%.2f", viewData.gainAmount),
                    color = getGainTextColor(viewData.gainAmount),
                )
            }
        }
    }
}

private fun getGainTextColor(gain: Double) =
    if (gain > 0) Color.Green else Color.Red

@Composable
@Preview
internal fun SecurityMovementGainPreview() {
    SecurityMovementGain(
        viewData = SecurityMovementGainViewData.EMPTY.copy(
            ticker = "AAPL",
            quantity = 2,
            cost = 123.445,
            gainPercent = 12.345,
            gainAmount = -1234.567,
        ),
    )
}

