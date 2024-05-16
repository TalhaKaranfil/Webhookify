package model

data class WebhookMessage(
    val formValues: Map<String, String> = emptyMap(),
    val timestamp: String = "",
    val hash: String = ""
)