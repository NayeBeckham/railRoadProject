#!/bin/bash

docker-compose up -d
sleep 2
DYNAMODB_ENDPOINT="http://localhost:9001"
CARS_TABLE="cars"
DESTINATION_TABLE="destination"
RECEIVER_TABLE="receiver"

create_cars_table() {
  echo "creating cars table: ${CARS_TABLE}"
  aws dynamodb create-table --endpoint-url $DYNAMODB_ENDPOINT --table-name $CARS_TABLE --no-cli-pager \
    --attribute-definitions AttributeName=id,AttributeType=S \
    AttributeName=name,AttributeType=S \
#    AttributeName=destination,AttributeType=S \
#    AttributeName=classificationTrack,AttributeType=S \
#    AttributeName=receiver,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH AttributeName=name,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5 \

}

create_receiver_table() {
  echo "creating receiver table: ${RECEIVER_TABLE}"
  aws dynamodb create-table --endpoint-url $DYNAMODB_ENDPOINT --table-name $RECEIVER_TABLE --no-cli-pager \
    --attribute-definitions AttributeName=id,AttributeType=S \
    AttributeName=receiver,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH AttributeName=receiver,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5 \

}

create_destination_table() {
  echo "creating destination table: ${DESTINATION_TABLE}"
  aws dynamodb create-table --endpoint-url $DYNAMODB_ENDPOINT --table-name $DESTINATION_TABLE --no-cli-pager \
    --attribute-definitions AttributeName=id,AttributeType=S \
    AttributeName=cityName,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH AttributeName=cityName,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5 \

}


#create_cars_table
#create_receiver_table
create_destination_table