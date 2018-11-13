package catalogservice.adapter.web;

import catalogservice.application.FindBooksUseCase;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Controller("/")
@RequiredArgsConstructor
public class BooksViewController {
  private final FindBooksUseCase findBooksUseCase;

  @Get
  @View
  public Single<ModelAndView> booksView() {
    return findBooksUseCase.invoke()
      .toList()
      .map(bookList -> {
        Map<String, ?> model = Map.of(
          "books", bookList,
          "numberOfBooks", bookList.size()
        );
        return new ModelAndView("booksView", model);
      });
  }
}
