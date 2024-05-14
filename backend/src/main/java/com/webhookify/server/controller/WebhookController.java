package com.webhookify.server.controller;

import com.webhookify.server.model.Webhook;
import com.webhookify.server.service.WebhookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/webhooks")
@Tag(name = "Webhookify API", description = "API for managing webhooks")
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping
    @Operation(summary = "Send a webhook", description = "Sends a webhook and stores it in runtime memory")
    public String sendWebhook(@RequestParam Map<String, String> formValues) {
        webhookService.processWebhook(formValues);
        return "Webhook received successfully!";
    }

    @GetMapping
    @Operation(summary = "Get all webhooks", description = "Retrieves all stored webhooks from runtime memory")
    public List<Webhook> getWebhooks() {
        return webhookService.getWebhooks();
    }
}