package view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import ui.theme.CustomColorPalette
import ui.theme.CustomTypography
import viewmodel.InboxViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun InboxScreen(viewModel: InboxViewModel) {
    val scrollState = rememberScrollState()

    var refreshTrigger by remember { mutableStateOf(0) }

    // LaunchedEffect to refresh every 15 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(15000)
            viewModel.fetchMessages()
            refreshTrigger++
        }
    }

    // Calculate the line length based on scroll progress
    val lineLength by derivedStateOf {
        val maxScroll = scrollState.maxValue.toFloat()
        val currentScroll = scrollState.value.toFloat()
        if (maxScroll == 0f) 0f else (currentScroll / maxScroll) * 100f
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .zIndex(1f) // Ensure the line is on top and cant be overlapped by msg box
        ) {
            drawLine(
                color = Color(0xFF04d63a),
                start = Offset(0f, 0f),
                end = Offset(size.width * (lineLength / 100f), 0f),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .zIndex(0f) // Ensure the messages are below the line
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
                                    Text(
                                        text = "$key: ", color = Color(0xFF04d63a), fontFamily = CustomTypography.h1.fontFamily,
                                        modifier = Modifier.background(
                                            CustomColorPalette.secondary,
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
                                fontFamily = CustomTypography.h1.fontFamily,
                            )
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                viewModel.fetchMessages()
                refreshTrigger++
            },
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF04d63a)),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("↻", fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}