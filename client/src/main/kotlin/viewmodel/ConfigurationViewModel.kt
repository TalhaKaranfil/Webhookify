package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

class ConfigurationViewModel {

    var getWebhooksUrl by mutableStateOf("")
        private set

    var message by mutableStateOf("")
        private set

    var messageColor by mutableStateOf(0xFF000000)
        private set

    init {
        loadUrlFromFile()
    }

    fun updateURL(newURL: String) {
        getWebhooksUrl = newURL
        println("getWebhooksUrl updated to $getWebhooksUrl")
        saveUrlToFile(getWebhooksUrl)
    }

    private fun saveUrlToFile(url: String) {
        try {
            // get the users documents directory
            val documentsDir = File(System.getProperty("user.home"), "Documents/webhookify")
            if (!documentsDir.exists()) { // if the directory does not exist, create it
                documentsDir.mkdirs()
            }
            // create a file in the documents directory
            val configFile = File(documentsDir, "webhookify.config")
            configFile.writeText("getWebhooksUrl=$url")
            println("URL saved to ${configFile.absolutePath}")
            message = "URL saved successfully to ${configFile.absolutePath}"
            messageColor = 0xFF04d63a // Green
        } catch (e: Exception) {
            println("Error saving URL to file: ${e.message}")
            message = "Failed to save URL"
            messageColor = 0xFFFF0000 // Red
        }
    }

    private fun loadUrlFromFile() {
        try {
            // get the users config file
            val documentsDir = File(System.getProperty("user.home"), "Documents/webhookify")
            val configFile = File(documentsDir, "webhookify.config")
            if (configFile.exists()) {
                // if the file exists, read the URL from it
                val configLines = configFile.readLines()
                for (line in configLines) {
                    if (line.startsWith("getWebhooksUrl=")) {
                        // extract the URL from the line and display it
                        getWebhooksUrl = line.substringAfter("getWebhooksUrl=")
                        println("getWebhooksUrl loaded from file: $getWebhooksUrl")
                        break
                    }
                }
            }
        } catch (e: Exception) {
            println("Error loading URL from file: ${e.message}")
        }
    }
}