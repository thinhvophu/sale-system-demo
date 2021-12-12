#!/bin/bash

echo "Starting..."
./gradlew wrapper
./gradlew clean

echo "Building source code..."
./gradlew bootJar

echo "Clean up running containers..."
./stop.sh
docker compose rm -f

echo "Building docker images..."
docker compose build

echo "Starting docker containers..."
docker compose up -d;

echo "Containers are up and running in background, exit terminal..."

sleep 5