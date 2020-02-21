package ba.playground.reactive.service;

import ba.playground.reactive.model.Book;
import ba.playground.reactive.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Mono<Book> addBook(Book book) {
        Mono<Book> save = bookRepository.save(book);
        return save;
    }

    public Flux<Book> findAllBooks() {
        return bookRepository.findAll();
    }


    // TODO: TO IMPLEMENT

    /*
    public Flux<Book> findByPublisher () {
        return null;
    }

    public Flux<Book> findByTitle () {
        return null;
    }

    public Flux<Book> searchTitlesByKeyword (String keyword) {
        return null;
    }

     */
}
