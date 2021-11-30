#!/bin/bash

#Pull new changes


#Prepare Jar
mvn clean
mvn package

#Ensure, that docker-compose stopped
docker-compose stop


#Add evironment variables
export BOT_NAME=$1
export BOT_TOKEN=$2

#Start new deployment
docker-compose up --build -d