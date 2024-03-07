package services

import jakarta.inject.Singleton
import models.DestinationModel
import repositories.DestinationRepository

@Singleton
open class DestinationService(
    private val destinationRepository: DestinationRepository) {

    fun createDestination(destination: DestinationModel) {
        return destinationRepository.createDestination(destination);
    }

    open fun getDestinations(): List<DestinationModel> {
        return destinationRepository.getDestinations();
    }

    fun updateDestinationPriority(id: String, cityName: String, priority: String){
        return destinationRepository.updateDestinationPriority(id, cityName, priority);
    }
    fun deleteDestination(id: String, cityName: String){
        return destinationRepository.deleteDestination(id, cityName);
    }
}