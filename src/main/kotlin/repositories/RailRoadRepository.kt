package repositories

import jakarta.inject.Singleton
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Singleton
class RailRoadRepository(
    private val dynamoDbClient: DynamoDbEnhancedClient){
}