package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ui.theme.CustomColorPalette
import viewmodel.ConfigurationViewModel

@Composable
fun ConfigurationScreen(viewModel: ConfigurationViewModel) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        URLInput()
    }


}


@Preview
@Composable
fun URLInput() {
    var text by remember { mutableStateOf("https://") }

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

        )
    )
}

