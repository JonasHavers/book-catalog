package catalogservice.application;

import catalogservice.domain.Book;
import io.reactivex.Flowable;

public interface FindBooksPort {
  Flowable<Book> books();
}
