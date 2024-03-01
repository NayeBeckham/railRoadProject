package client

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Factory
class DynamoDbConnection {

//    @Bean
    @Singleton
    fun dynamoDbClient(): DynamoDbClient {
        return DynamoDbClient.builder()
            .region(Region.EU_WEST_1)
            .endpointOverride(URI.create("http://localhost:9001"))
            .build()
    }
}