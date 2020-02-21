package ba.playground.reactive.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloExampleController {

    @GetMapping(value = "/hello")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<List<String>>> hello() {
        List<String> strings = Arrays.asList("test1,test2,test3,test4,test5,test6".split(","));
        return new ResponseEntity<>(Flux.just(strings), HttpStatus.OK);
    }
}
