package view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.theme.CustomColorPalette
import viewmodel.InboxViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun InboxScreen(viewModel: InboxViewModel) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        viewModel.messages.asReversed().forEachIndexed { index, message ->
            val backgroundColor = if (index % 2 == 0) Color(0xFF3b3b3b) else CustomColorPalette.primary

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.width(800.dp)
                    ) {
                        message.formValues.forEach { (key, value) ->
                            Row {
                                Text(text = "$key: ", color = Color(0xFF04d63a),
                                    modifier = Modifier.background(CustomColorPalette.secondary,
                                        shape = RoundedCornerShape(20)
                                    )
                                        .padding(2.dp)
                                )
                                Text(text = value, modifier = Modifier.padding(start = 8.dp))
                            }
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.background(CustomColorPalette.secondary, shape = RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        val dateTime = LocalDateTime.parse(message.timestamp)
                        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

                        Text(
                            text = dateTime.format(dateFormatter),
                        )
                        Text(
                            text = dateTime.format(timeFormatter),
                        )
                        Text(
                            text = message.hash,
                            color = Color(0xFF04d63a),
                        )
                    }
                }
            }
        }
    }
}