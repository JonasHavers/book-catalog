# book-catalog

This is an example application written with [Micronaut](http://micronaut.io).
It publishes a catalog of books from a MongoDB database through an HTTP API and [Handlebars](http://jknack.github.io/handlebars.java/) view.

Please note that the Docker setup is explicitly not intended for use in production.

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
