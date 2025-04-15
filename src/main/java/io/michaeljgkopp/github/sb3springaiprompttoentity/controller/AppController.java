package io.michaeljgkopp.github.sb3springaiprompttoentity.controller;

import io.michaeljgkopp.github.sb3springaiprompttoentity.entity.Book;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final ChatClient chatClient;

    public AppController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/")
     public String home() {
         return chatClient.prompt("Generate a book recommendation for a book on AI and coding")
                 .call()
                 .content();
     }

    @GetMapping("/book")
     public Book getBook() {
         return chatClient.prompt("Generate a complete book recommendation for a book on AI and coding")
                 .call()
                 .entity(Book.class);
     }
}
