package ba.playground.reactive.repository;

import ba.playground.reactive.model.Message;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface MessageRepository extends ReactiveCassandraRepository<Message, Long> {
}
