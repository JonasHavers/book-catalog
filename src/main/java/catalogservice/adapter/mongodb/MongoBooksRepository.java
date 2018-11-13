package catalogservice.adapter.mongodb;

import catalogservice.application.FindBooksPort;
import catalogservice.domain.Book;
import com.mongodb.client.model.Sorts;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class MongoBooksRepository implements FindBooksPort {
  private final MongoClient mongoClient;
  private final MongoConfigurationProperties mongoConfig;

  @Override
  public Flowable<Book> books() {
    return Flowable.fromPublisher(
      booksCollection()
        .find()
        .sort(Sorts.ascending("title"))
    );
  }

  private MongoCollection<Book> booksCollection() {
    return booksDatabase()
      .getCollection(mongoConfig.collection, Book.class);
  }

  private MongoDatabase booksDatabase() {
    return mongoClient.getDatabase(mongoConfig.database);
  }
}
