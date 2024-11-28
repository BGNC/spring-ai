package com.bgnc.springai.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatModel chatModel;

    public String getResponse(String prompt){
        return chatModel.call(prompt);
    }

    public String getResponseOptions(@RequestParam String prompt){
        ChatResponse response =   chatModel.call(
                new Prompt(
                prompt,
                OpenAiChatOptions.builder()
                        .withModel("gpt-4o") // gpt-4o
                        .withTemperature(0.2)
                        .build()
        ));

        return response.getResult().getOutput().getContent();
    }

}
