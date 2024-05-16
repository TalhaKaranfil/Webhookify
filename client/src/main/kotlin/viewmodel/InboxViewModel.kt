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

class InboxViewModel {
    var messages by mutableStateOf(listOf<WebhookMessage>())
        private set

    init {
        fetchMessages()
    }

    // Fetches messages from the webhookify server
    fun fetchMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://webhookify.onrender.com/api/webhooks")
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
}