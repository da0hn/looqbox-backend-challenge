[![CI](https://github.com/da0hn/looqbox-backend-challenge/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/da0hn/looqbox-backend-challenge/actions/workflows/gradle.yml)
![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

# Backend Looqbox Challenge

[Looqbox Challenge Doc](./code-challenge.md#guidelines)

## Run application

- docker:
  > Run following the command in terminal to build image: `docker image build -t pokemon-service .` or use `sh create-image.sh`

  > Then run the command to run the container`docker run --name pokemon-service -p 8080:8080 pokemon-service`

- docker-compose:
  > docker-compose up -d

## Diagram

![diagrama](./backend-challenge-diagram.jpg)

### Links

* [gradle projects github actions](https://tomgregory.com/build-gradle-projects-with-github-actions/)
* [jacoco badge generator](https://github.com/cicirello/jacoco-badge-generator)
* [definitive guide jacoco - reflectoring](https://reflectoring.io/jacoco/)
* [gradle jacoco user guide](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
