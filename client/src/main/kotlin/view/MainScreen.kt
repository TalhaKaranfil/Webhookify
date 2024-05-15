package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.CustomColorPalette
import viewmodel.ConfigurationViewModel
import viewmodel.MainViewModel

enum class Screen {
    MAIN, CONFIGURATION
}

@Composable
fun MainScreen(mainViewModel: MainViewModel, configurationViewModel: ConfigurationViewModel) {
    var currentScreen by remember { mutableStateOf(Screen.MAIN) }
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()
        Row(modifier = Modifier.fillMaxSize()) {
            NavigationSidebar(onScreenChange = { screen -> currentScreen = screen })
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        when (currentScreen) {
                            Screen.MAIN -> MainContent(mainViewModel)
                            Screen.CONFIGURATION -> ConfigurationScreen(configurationViewModel)
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun MainContent(viewModel: MainViewModel) {
    Button(onClick = {
        viewModel.updateText()
    }) {
        Text(viewModel.text, style = MaterialTheme.typography.button)
    }
    // TODO: other content views
}

@Composable
fun NavigationSidebar(onScreenChange: (Screen) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .width(300.dp)
            .fillMaxSize()
            .background(CustomColorPalette.secondary)
    ) {
        Button(onClick = { onScreenChange(Screen.MAIN) },
            modifier = Modifier
                .size(width = 250.dp, height = 100.dp)
        ) {
            Text("Main Screen", style = MaterialTheme.typography.button)
        }
        Button(onClick = { onScreenChange(Screen.CONFIGURATION) },
            modifier = Modifier
                .size(width = 250.dp, height = 100.dp)
        ) {
            Text("Configuration", style = MaterialTheme.typography.button)
        }
        // TODO: add more later
    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(CustomColorPalette.secondary) // Apply custom secondary color as background
    ) {
        Text(
            text = "Top Bar",
            color = CustomColorPalette.onSecondary,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}