package com.webhookify.server.controller;

import com.webhookify.server.model.Webhook;
import com.webhookify.server.service.WebhookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping
    public String receiveWebhook(@RequestParam Map<String, String> formValues) {
        webhookService.processWebhook(formValues);
        return "Webhook received successfully!";
    }

    @GetMapping
    public List<Webhook> getWebhooks() {
        return webhookService.getWebhooks();
    }
}