FROM adoptopenjdk/openjdk11:jdk-11.28
COPY build/libs/*-all.jar book-catalog.jar
CMD java ${JAVA_OPTS} -jar book-catalog.jar
