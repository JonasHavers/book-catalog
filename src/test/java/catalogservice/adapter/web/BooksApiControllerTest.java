package catalogservice.adapter.web;

import catalogservice.domain.Book;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BooksApiControllerTest {

  @Test
  public void shouldReturnBooks() throws Exception {
    try (EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class)) {
      try (RxHttpClient client = RxHttpClient.create(server.getURL())) {
        // given
        HttpRequest<?> request = HttpRequest
          .GET("/api/books/");
        // when
        HttpResponse<List> response = client
          .toBlocking()
          .exchange(request, Argument.of(List.class, Book.class));
        // then
        HttpStatus responseStatus = response.status();
        assertEquals(HttpStatus.OK, responseStatus);
        // and
        List body = response.body();
        assertNotNull(body);
        // and
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
        assertTrue(body.contains(book));
      }
    }
  }
}
