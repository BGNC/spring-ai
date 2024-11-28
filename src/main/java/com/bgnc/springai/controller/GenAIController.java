package com.bgnc.springai.controller;

import com.bgnc.springai.service.ChatService;
import com.bgnc.springai.service.ImageService;
import com.bgnc.springai.service.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.bgnc.springai.config.RestApis.*;

@RestController
@RequiredArgsConstructor
public class GenAIController {

    private final ImageService imageService;
    private final ChatService chatService;
    private final RecipeService recipeService;

    @GetMapping(ASK_AI)
    public String getResponse(@RequestParam String prompt){
        return  chatService.getResponse(prompt);
    }

    @GetMapping(ASK_AI_OPTIONS)
    public String getResponseOptions(@RequestParam String prompt){
        return  chatService.getResponseOptions(prompt);
    }


    /*

    @GetMapping(GENERATE_IMAGE)
    public void generateImages(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse=  imageService.generateImage(prompt);
        String imageUrl =  imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(imageUrl);
    }*/

    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") int N,
                                       @RequestParam(defaultValue = "1024")int width,
                                       @RequestParam(defaultValue = "1024")int height) throws IOException {
        ImageResponse imageResponse=  imageService.generateImage(prompt,quality,N,width,height);

        // Streams to get urls from the Image

        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result->result.getOutput().getUrl()).toList();

        return imageUrls;
    }


    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                      @RequestParam(defaultValue = "any") String cuisine,
                                      @RequestParam(defaultValue = "") String dietaryRestrictions ){

        return  recipeService.createRecipe(ingredients,cuisine,dietaryRestrictions);
    }





}
