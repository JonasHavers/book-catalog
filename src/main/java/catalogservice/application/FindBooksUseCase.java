package catalogservice.application;

import catalogservice.domain.Book;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class FindBooksUseCase {
  private final FindBooksPort findBooksPort;

  public Flowable<Book> invoke() {
    return findBooksPort.books();
  }
}
