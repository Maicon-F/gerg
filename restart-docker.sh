#!/bin/bash

set -e

echo "Stopping all running containers..."
docker ps -q | xargs -r docker stop

echo "Removing all containers..."
docker ps -aq | xargs -r docker rm -f

echo "Removing all images..."
docker images -q | xargs -r docker rmi -f

echo "Building your image from Dockerfile as gerg.app..."
docker build -t gerg.app .

echo "Running docker-compose..."
docker compose up -d





echo "populating database with initial HC data"
docker exec -i gerg-db-1 psql -U gerg -d   gergdb < init.sql

echo "All done!"
