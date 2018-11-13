#!/usr/bin/env bash -e
./gradlew assemble

export MONGO_DATABASE=book-catalog
export MONGO_COLLECTION=books

docker-compose up --no-color \
  --detach \
  --build \
  --renew-anon-volumes

docker-compose exec -T book-catalog-db \
  mongoimport --verbose \
    --db ${MONGO_DATABASE} \
    --collection ${MONGO_COLLECTION} \
    --type json \
    --file /data/db-import/books.json
