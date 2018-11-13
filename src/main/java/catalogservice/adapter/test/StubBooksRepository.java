package catalogservice.adapter.test;

import catalogservice.adapter.mongodb.MongoBooksRepository;
import catalogservice.application.FindBooksPort;
import catalogservice.domain.Book;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.reactivex.Flowable;

import javax.inject.Singleton;

@Singleton
@Replaces(bean = MongoBooksRepository.class)
@Requires(env = Environment.TEST)
public class StubBooksRepository implements FindBooksPort {

  @Override
  public Flowable<Book> books() {
    Book book = new Book(
      "It Doesn't Have to Be Crazy at Work",
      "Jason Fried",
      "David Heinemeier Hansson",
      "0062874780",
      "9780062874788",
      240,
      2018,
      2018,
      38900866L
    );
    return Flowable.just(book);
  }
}
