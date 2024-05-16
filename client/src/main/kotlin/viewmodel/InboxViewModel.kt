package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.WebhookMessage
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class InboxViewModel {
    var messages by mutableStateOf(listOf<WebhookMessage>())
        private set

    init {
        fetchMessages()
    }

    // Fetches messages from the webhookify server
    fun fetchMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(getWebhookURL())
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            try {
                // Read the response from the server and parse it into a list of WebhookMessage objects
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val messageType = object : TypeToken<List<WebhookMessage>>() {}.type
                val fetchedMessages: List<WebhookMessage> = Gson().fromJson(response, messageType)

                withContext(Dispatchers.Main) {
                    messages = fetchedMessages
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.disconnect()
            }
        }
    }

    fun getWebhookURL(): String {
        var webhookUrl = ""
        try {
            // Get the user's config file
            val documentsDir = File(System.getProperty("user.home"), "Documents/webhookify")
            val configFile = File(documentsDir, "webhookify.config")
            if (configFile.exists()) {
                // If the file exists, read the URL from it
                val configLines = configFile.readLines()
                for (line in configLines) {
                    if (line.startsWith("getWebhooksUrl=")) {
                        // Extract the URL from the line
                        webhookUrl = line.substringAfter("getWebhooksUrl=")
                        break
                    }
                }
            }
        } catch (e: Exception) {
            println("Error loading URL from file: ${e.message}")
        }
        return webhookUrl
    }
}