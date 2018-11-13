package catalogservice.adapter.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Controller("/booksApi")
public class BooksApiController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }
}