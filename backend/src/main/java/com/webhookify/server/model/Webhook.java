package com.webhookify.server.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Webhook {

    private Map<String, String> formValues;
    private LocalDateTime timestamp;
    private String hash;

    public Map<String, String> getFormValues() {
        return formValues;
    }

    public void setFormValues(Map<String, String> formValues) {
        this.formValues = formValues;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}