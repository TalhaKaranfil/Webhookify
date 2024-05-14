package com.webhookify.server.model;

import java.util.Map;

public class Webhook {

    private Map<String, String> formValues;

    public Map<String, String> getFormValues() {
        return formValues;
    }

    public void setFormValues(Map<String, String> formValues) {
        this.formValues = formValues;
    }
}