package ba.playground.reactive.service;

import ba.playground.reactive.model.Message;
import ba.playground.reactive.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public void addMessage(Message message) {
        Flux<Message> save = messageRepository.saveAll(Arrays.asList(message));
        save.subscribe();
    }

    public Flux<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
