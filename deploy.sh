#!/bin/bash
mvn clean install -DskipTests
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker volume rm $(docker volume ls |awk '{print $2}')
rm ./logs/server*
docker-compose build
docker-compose up
