package repositories

import jakarta.inject.Singleton
import models.ReceiverModel
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*
import java.util.*

@Singleton
class ReceiverRepository(private val dynamoDbClient: DynamoDbClient) {
    private val _tableName = "receiver"

    fun createReceiver(receiver: ReceiverModel){
        dynamoDbClient.putItem(
            PutItemRequest.builder()
                .tableName(_tableName)
                .item(
                    mapOf(
                        "id" to AttributeValue.builder().s(receiver.id.toString()).build(),
                        "receiver" to AttributeValue.builder().s(receiver.receiver).build(),
                        "priority" to AttributeValue.builder().n(receiver.priority.toString()).build()
                    )
                )
                .build())
    }

    fun getReceivers(): List<ReceiverModel> {
        val scanRequest = ScanRequest.builder().tableName(_tableName).build()

        val response = dynamoDbClient.scan(scanRequest)
        val items = response.items()

        return items.map {
                receiver ->
            ReceiverModel(
                id = UUID.fromString(receiver["id"]!!.s()),
                receiver = receiver["receiver"]!!.s(),
                priority = receiver["priority"]!!.n().toInt(),
            )
        }
    }


    fun updateReceiverPriority(id: String, receiver: String, priority: String) {
        dynamoDbClient.updateItem(
            UpdateItemRequest.builder()
                .tableName(_tableName).key(
                    mapOf(
                        "id" to AttributeValue.builder().s(id).build(),
                        "receiver" to AttributeValue.builder().s(receiver).build()
                    )
                ).updateExpression("SET priority = :priority")
                .expressionAttributeValues(
                    mapOf(
                        ":priority" to AttributeValue.builder().s(priority).build()
                    )
                )
                .build()
        )
    }

    fun deleteReceiver(id: String, receiver: String) {
        dynamoDbClient.deleteItem(
            DeleteItemRequest.builder()
                .tableName(_tableName)
                .key(
                    mapOf(
                        "id" to AttributeValue.builder().s(id).build(),
                        "receiver" to AttributeValue.builder().s(receiver).build()
                    )
                )
                .build()
        )
    }
}