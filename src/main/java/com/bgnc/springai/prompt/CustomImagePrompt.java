package com.bgnc.springai.prompt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.stereotype.Service;

public class CustomImagePrompt extends ImagePrompt {
    private final String size;

    public CustomImagePrompt(String prompt, String size) {
        super(prompt); // Call the existing constructor
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}