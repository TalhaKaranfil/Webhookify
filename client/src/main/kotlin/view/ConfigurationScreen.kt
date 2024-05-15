package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.theme.CustomColorPalette
import viewmodel.ConfigurationViewModel

@Composable
fun ConfigurationScreen(viewModel: ConfigurationViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        URLInput(viewModel)
        if (viewModel.message.isNotEmpty()) {
            Text(
                text = viewModel.message,
                color = Color(viewModel.messageColor),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun URLInput(viewModel: ConfigurationViewModel) {
    var text by remember { mutableStateOf(viewModel.url) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(30.dp)

    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("URL") },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF04d63a),
                unfocusedBorderColor = CustomColorPalette.onBackground,
                cursorColor = Color(0xFF04d63a),
                unfocusedLabelColor = CustomColorPalette.onBackground,
                focusedLabelColor = Color(0xFF04d63a),
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { viewModel.updateURL(text) }) {
            Text("Save")
        }
    }
}