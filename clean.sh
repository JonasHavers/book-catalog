#!/usr/bin/env bash -e
docker-compose down --rmi local --volumes
./gradlew clean
