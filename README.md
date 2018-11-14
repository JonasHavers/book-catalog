# book-catalog

This is a basic example application written with [Micronaut](http://micronaut.io)
that publishes a catalog of books from a MongoDB database via an HTTP API and a server-side [Handlebars](http://jknack.github.io/handlebars.java/) view.

The example also includes Lombok integration, Jackson configuration, reactive MongoDB connectivity, ConfigurationProperties and a test environment stub.

Please note that additional security configuration is required to use the project in production.

## Requirements

* [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) (tested with [11.0.1-zulu](https://www.azul.com/downloads/zulu/))
* [Docker Engine (Community)](https://store.docker.com/search?type=edition&offering=community)
* [Docker Compose](https://docs.docker.com/compose/install/)

## How to run the service?

Start the service by executing the bash-script `run.sh`.

This will:
- build the Micronaut application JAR file (using the Gradle wrapper),
- run a Docker multi-container application in daemon mode (using docker-compose),
  - with a MongoDB container
  - with a Micronaut application container
- seed the MongoDB database with some books (using docker-compose and mongoimport).

You should be able to access [http://localhost:8080/](http://localhost:8080/)
and [http://localhost:8080/api/books](http://localhost:8080/api/books) 
within a few seconds after the Docker containers are started.

If you are using Docker machine the host might be different (e.g. 192.168.99.100).
 
Port 8080 of the Micronaut container is exposed to port 8080 by default.
You can change the local port by defining the environment variable 
`APPLICATION_PORT`:
```
APPLICATION_PORT=8888 ./run.sh
```

You can track the logfiles via `docker-compose logs -f`.

## How to stop the service?

Stop the service by executing the bash-script `clean.sh`.

This will:
- remove the "build/" directory (from the Gradle build),
- stop (or kill after 10sec) the Docker multi-container application (using docker-compose),
- remove all attached Docker resources,
- remove the Micronaut application image.

## How was the project created?

The project itself, the Java beans and controller skeletons were scaffolded using the 
[Micronaut CLI](https://docs.micronaut.io/latest/guide/index.html#buildCLI):

```
$ mn create-app catalogservice.book-catalog --features mongo-reactive
| Generating Java project...
| Application created at /Volumes/projects/github.com/JonasHavers/book-catalog
$ cd book-catalog/
$ mn
| Starting interactive mode...
| Enter a command name to run. Use TAB for completion:
mn> create-bean catalogservice.application.FindBooksUseCase
| Rendered template Bean.java to destination src/main/java/catalogservice/application/FindBooksUseCase.java
mn> create-bean catalogservice.adapter.mongodb.MongoBooksRepository
| Rendered template Bean.java to destination src/main/java/catalogservice/adapter/mongodb/MongoBooksRepository.java
mn> create-controller catalogservice.adapter.web.BooksApiController
| Rendered template Controller.java to destination src/main/java/catalogservice/adapter/web/BooksApiController.java
| Rendered template ControllerTest.java to destination src/test/java/catalogservice/adapter/web/BooksApiControllerTest.java
mn> create-bean catalogservice.adapter.test.StubBooksRepository
| Rendered template Bean.java to destination src/main/java/catalogservice/adapter/test/StubBooksRepository.java
```

The Lombok dependency and annotation processor were added to the project in *build.gradle* right before Micronaut's annotation processors:

```
compileOnly "org.projectlombok:lombok:1.18.4"             // added
annotationProcessor "org.projectlombok:lombok:1.18.4"     // added
annotationProcessor "io.micronaut:micronaut-inject-java"
```

The Handlebars dependency was added to the project in *build.gradle* to support server-side view rendering:

```
runtime "com.github.jknack:handlebars:4.1.2"
```

The controller that is responsible for the routing and the view model creation was also scaffolded:

```
$ mn create-controller catalogservice.adapter.web.BooksViewController
| Rendered template Controller.java to destination src/main/java/catalogservice/adapter/web/BooksViewController.java
| Rendered template ControllerTest.java to destination src/test/java/catalogservice/adapter/web/BooksViewControllerTest.java
```

Implementation and configuration followed the scaffolding.
For further details, please refer to the Git history.

## Author

Jonas Havers ([@JonasHavers](https://twitter.com/JonasHavers)) - [https://jonas-havers.de](https://jonas-havers.de)
