import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import view.MainScreen
import viewmodel.MainViewModel

@Composable
fun App() {
    val viewModel = MainViewModel()
    MaterialTheme {
        MainScreen(viewModel)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}