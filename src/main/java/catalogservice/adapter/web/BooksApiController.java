package catalogservice.adapter.web;

import catalogservice.application.FindBooksUseCase;
import catalogservice.domain.Book;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;

@Controller("/api/books")
@RequiredArgsConstructor
public class BooksApiController {
  private final FindBooksUseCase findBooksUseCase;

  @Get("/")
  public Flowable<Book> books() {
    return findBooksUseCase.invoke();
  }
}
