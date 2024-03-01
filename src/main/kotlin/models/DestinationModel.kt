package models

import enums.DestinationEnum
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

@Serdeable.Serializable
@Serdeable.Deserializable
@Introspected
@DynamoDbBean
data class DestinationModel(

    @get:DynamoDbPartitionKey
    val id: String?,

    @get:DynamoDbAttribute(value = "cityName")
    val city: DestinationEnum
)



