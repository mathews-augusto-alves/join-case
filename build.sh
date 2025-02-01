#!/bin/bash

echo "Iniciando o mvn clean install nos projetos..."

cd ./lib
mvn clean install
cd ..

cd ./app-control
mvn clean install
cd ..

cd ./service-consumer
mvn clean install
cd ..

cd ./project
npm ci
cd ..

echo "Buildando e subindo os containers com Docker Compose..."
docker-compose up --build -d
