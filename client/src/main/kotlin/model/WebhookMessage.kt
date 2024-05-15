package model

data class WebhookMessage(
    val formValues: Map<String, String>,
    val timestamp: String,
    val hash: String
)