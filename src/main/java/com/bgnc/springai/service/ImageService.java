package com.bgnc.springai.service;

import com.bgnc.springai.prompt.CustomImagePrompt;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final OpenAiImageModel openAiImageModel;
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    public ImageResponse generateImage(String prompt, String quality,int n, int width, int height) {
        /*ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(
                        prompt,
                        OpenAiImageOptions.builder()
                                .withModel("dall-e-2")
                                .withQuality("hd")
                                .withN(3)
                                .withHeight(1024)
                                .withWidth(1024)
                                .build())
        );*/

        ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(
                        prompt,
                        OpenAiImageOptions.builder()
                                .withModel("dall-e-2")
                                .withQuality(quality)
                                .withN(n)
                                .withHeight(height)
                                .withWidth(width)
                                .build())
        );


        return imageResponse;
    }


}