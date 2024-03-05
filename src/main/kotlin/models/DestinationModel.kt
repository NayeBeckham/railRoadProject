package models

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import org.jetbrains.annotations.NotNull
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.util.UUID

@Serdeable.Serializable
@Serdeable.Deserializable
@Introspected
@DynamoDbBean
data class DestinationModel(

    @get:DynamoDbPartitionKey
    val id: UUID? = UUID.randomUUID(),

    @get:DynamoDbAttribute(value = "cityName")
    @NotNull
    val city: String,

    @get:DynamoDbAttribute(value = "priority")
    @NotNull
    val priority: Int= -1
)



