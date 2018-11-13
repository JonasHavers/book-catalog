package catalogservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
  private String title;
  private String author;
  private String additionalAuthors;
  private String isbn;
  private String isbn13;
  private Integer numberOfPages;
  private Integer yearPublished;
  private Integer originalPublicationYear;
  private Long goodreadsBookId;
}
