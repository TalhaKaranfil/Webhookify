package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Row(modifier = Modifier.fillMaxSize()) {
        NavigationSidebar()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    Button(onClick = {
                        viewModel.updateText()
                    }) {
                        Text(viewModel.text)
                    }
                    // TODO: other content views
                }
            }
        )
    }
}

@Preview
@Composable
fun NavigationSidebar() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .width(300.dp)
            .fillMaxSize()
    ) {

        // TODO: navigation items
        Button( onClick = { /*TODO*/ },
            modifier = Modifier
                .size(width = 250.dp, height = 100.dp)
        ) {
            Text("Navigation Item")
        }
        // --------------------------------
        Button( onClick = { /*TODO*/ },
            modifier = Modifier
                .size(width = 250.dp, height = 100.dp)
        ) {
            Text("Navigation Item")
        }
        // --------------------------------
        Button( onClick = { /*TODO*/ },
            modifier = Modifier
                .size(width = 250.dp, height = 100.dp)
        ) {
            Text("Navigation Item")
        }
        // --------------------------------
        Button( onClick = { /*TODO*/ },
            modifier = Modifier
                .size(width = 250.dp, height = 100.dp)
        ) {
            Text("Navigation Item")
        }
        // --------------------------------
        Button( onClick = { /*TODO*/ },
            modifier = Modifier
                .size(width = 250.dp, height = 100.dp)
        ) {
            Text("Navigation Item")
        }
        // --------------------------------
    }
}