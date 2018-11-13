package catalogservice.adapter.web;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/booksView")
public class BooksViewController {

  @Get("/")
  public HttpStatus index() {
    return HttpStatus.OK;
  }
}
