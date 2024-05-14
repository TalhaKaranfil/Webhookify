package view

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Button(onClick = {
        viewModel.updateText()
    }) {
        Text(viewModel.text)
    }
}