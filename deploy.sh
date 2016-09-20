#!/bin/bash
mvn clean install
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker volume rm $(docker volume ls |awk '{print $2}')
docker-compose build
docker-compose up
