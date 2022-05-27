#!/usr/bin/env bash

./gradlew build

docker image build -t pokemon-service .
