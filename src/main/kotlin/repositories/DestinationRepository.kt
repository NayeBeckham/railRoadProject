package repositories

import enums.DestinationEnum
import jakarta.inject.Singleton
import models.DestinationModel
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import software.amazon.awssdk.services.dynamodb.model.ScanRequest
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest

@Singleton
class DestinationRepository(private val dynamoDbClient: DynamoDbClient) {
     private val _tableName = "destination";

    fun createDestination(destination: DestinationModel){
        dynamoDbClient.putItem(
            PutItemRequest.builder()
            .tableName(_tableName)
            .item(
                mapOf(
                    "id" to AttributeValue.builder().s(destination.id).build(),
                    "cityName" to AttributeValue.builder().s(destination.city.cityName).build(),
                    "priority" to AttributeValue.builder().n(destination.city.priority.toString()).build()
                )
            )
            .build())

        //dynamoDbClient.close()
    }

    fun getDestinations(): List<DestinationModel> {
        val scanRequest = ScanRequest.builder().tableName(_tableName).build()

        val response = dynamoDbClient.scan(scanRequest)
        val items = response.items()

        return items.map {
            destination ->
            DestinationModel(
                city = DestinationEnum.entries.find{ it.cityName == destination["cityName"]?.s() }!!,
                id = destination["id"]?.s(),
            )
        }
    }


    fun updateDestinationPriority(id: String, cityName: String, priority: String) {
        dynamoDbClient.updateItem(
            UpdateItemRequest.builder()
                .tableName(_tableName).key(
                    mapOf(
                        "id" to AttributeValue.builder().s(id).build(),
                        "cityName" to AttributeValue.builder().s(cityName).build()
                    )
                ).updateExpression("SET priority = :priority")
                .expressionAttributeValues(
                    mapOf(
                        ":priority" to AttributeValue.builder().n(priority).build()
                    )
                )
                .build()
        )
    }

    fun deleteDestination(id: String, cityName: String) {
        dynamoDbClient.deleteItem(
            DeleteItemRequest.builder()
                .tableName(_tableName)
                .key(
                    mapOf(
                        "id" to AttributeValue.builder().s(id).build(),
                        "cityName" to AttributeValue.builder().s(cityName).build()
                    )
                )
                .build()
        )

        //dynamoDbClient.close()
    }
}