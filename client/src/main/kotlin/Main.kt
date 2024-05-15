import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.theme.CustomTheme
import view.MainScreen
import viewmodel.MainViewModel

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(1280.dp, 720.dp))
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        resizable = false,
        title = "Webhookify"
    ) {
        val viewModel = MainViewModel()
        CustomTheme {
            MainScreen(viewModel)
        }
    }
}