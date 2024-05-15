import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.theme.CustomTheme
import view.MainScreen
import viewmodel.MainViewModel
import viewmodel.ConfigurationViewModel
import viewmodel.InboxViewModel

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(1280.dp, 720.dp))
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        resizable = false,
        title = "Webhookify"
    ) {
        val mainViewModel = MainViewModel()
        val configurationViewModel = ConfigurationViewModel()
        val inboxViewModel = InboxViewModel()
        CustomTheme {
            MainScreen(mainViewModel, configurationViewModel, inboxViewModel)
        }
    }
}