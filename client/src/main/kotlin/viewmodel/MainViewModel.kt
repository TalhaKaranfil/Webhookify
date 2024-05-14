package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainViewModel {
    var text by mutableStateOf("Hello, World!")
        private set

    fun updateText() {
        text = "Hello, Desktop!"
    }
}