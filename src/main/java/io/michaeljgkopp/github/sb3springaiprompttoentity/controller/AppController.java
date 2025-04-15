package io.michaeljgkopp.github.sb3springaiprompttoentity.controller;

         import io.michaeljgkopp.github.sb3springaiprompttoentity.entity.Book;
         import org.springframework.ai.chat.client.ChatClient;
         import org.springframework.ai.chat.prompt.PromptTemplate;
         import org.springframework.http.ResponseEntity;
         import org.springframework.web.bind.annotation.GetMapping;
         import org.springframework.web.bind.annotation.RequestParam;
         import org.springframework.web.bind.annotation.RestController;

         import java.util.Map;

         @RestController
         public class AppController {
             private final ChatClient chatClient;
             private final String prompt = "Generate a book recommendation for a book on %s in %s.";

             public AppController(ChatClient.Builder builder) {
                 this.chatClient = builder.build();
             }

             @GetMapping("/")
             public ResponseEntity<String> getRecommendation(
                     @RequestParam(defaultValue = "AI and coding") String topic,
                     @RequestParam(defaultValue = "English") String language) {

                 String response = chatClient.prompt(prompt.formatted(topic, language))
                         .call()
                         .content();

                 return ResponseEntity.ok(response);
             }

             @GetMapping("/book")
             public ResponseEntity<Book> getBook(
                     @RequestParam(defaultValue = "AI and coding") String topic,
                     @RequestParam(defaultValue = "English") String language) {

                 Book book = chatClient.prompt(prompt.formatted(topic, language))
                         .call()
                         .entity(Book.class);

                 return ResponseEntity.ok(book);
             }
         }