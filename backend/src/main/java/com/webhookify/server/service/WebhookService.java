package com.webhookify.server.service;

import com.webhookify.server.model.Webhook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WebhookService {

    private final List<Webhook> webhooks = new ArrayList<>();

    public void processWebhook(Map<String, String> formValues) {
        Webhook webhook = new Webhook();
        webhook.setFormValues(formValues);
        webhooks.add(webhook);
    }

    public List<Webhook> getWebhooks() {
        return webhooks;
    }
}