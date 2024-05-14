package com.webhookify.server.service;

import com.webhookify.server.model.Webhook;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

@Service
public class WebhookService {

    private final List<Webhook> webhooks = new ArrayList<>();

    public void processWebhook(Map<String, String> formValues) {
        Webhook webhook = new Webhook();
        webhook.setFormValues(formValues);
        webhook.setTimestamp(LocalDateTime.now());
        webhook.setHash(generateHash(formValues.toString()));
        webhooks.add(webhook);
    }

    public List<Webhook> getWebhooks() {
        return webhooks;
    }

    private String generateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return byteArray2Hex(hashBytes).substring(0, 6);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String byteArray2Hex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}