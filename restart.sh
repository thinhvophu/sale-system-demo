#!/bin/bash

echo "stopping containers..."
./stop.sh

echo "starting continers..."
docker container start application-server
docker container start postgres-server

echo "Containers are up and running in background, exit terminal..."

sleep 5