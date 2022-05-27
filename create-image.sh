#!/usr/bin/env bash

echo "Building application"

./gradlew build

echo "Create docker image pokemon-service:latest"

docker image build -t pokemon-service .
