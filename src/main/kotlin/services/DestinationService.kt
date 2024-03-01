package services

import jakarta.inject.Singleton
import models.DestinationModel
import repositories.DestinationRepository
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

@Singleton
class DestinationService(
    private val dynamoDbClient: DynamoDbClient,
    private val destinationRepository: DestinationRepository) {

    fun main(){
        println(dynamoDbClient.listTables())
    }

    fun createDestination(destination: DestinationModel) {
        return destinationRepository.createDestination(destination);
    }

    fun getDestinations(): List<DestinationModel> {
        return destinationRepository.getDestinations();
    }

    fun updateDestinationPriority(id: String, cityName: String, newCityName: String){
        return destinationRepository.updateDestinationPriority(id, cityName, newCityName);
    }
    fun deleteDestination(id: String, priority: String){
        return destinationRepository.deleteDestination(id, priority);
    }
}