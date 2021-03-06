package ba.playground.reactive.service;

import ba.playground.reactive.model.Book;
import ba.playground.reactive.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Before
    public void setup() {
        Mono<Void> voidMono = bookRepository.deleteAll();
        voidMono.subscribe();
    }

    @Test
    public void add_book() {
        // Given
        HashSet<String> tags = new HashSet<>();
        tags.add("Tag 1");
        tags.add("Tag 2");

        Book book = new Book()
                .setId(UUID.randomUUID())
                .setPublisher("TestPublisher")
                .setTitle("Book 1")
                .setTags(tags);

        // When
        bookService.addBook(book);

        // Then
        Flux<Book> allBooks = bookService.findAllBooks();
        allBooks.subscribe();
        assertThat(allBooks).isNotNull();

        StepVerifier.create(allBooks)
                .expectNext(book)
                .expectComplete()
                .verify();
    }
}
