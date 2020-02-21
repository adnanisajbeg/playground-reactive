package ba.playground.reactive.web;

import ba.playground.reactive.model.Message;
import ba.playground.reactive.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/all")
    public Flux<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
