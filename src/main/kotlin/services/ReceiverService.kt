package services

import jakarta.inject.Singleton
import models.ReceiverModel
import repositories.ReceiverRepository

@Singleton
class ReceiverService(private val receiverRepository: ReceiverRepository) {

    fun createReceiver(receiver: ReceiverModel) {
        return receiverRepository.createReceiver(receiver)
    }

    fun getReceivers(): List<ReceiverModel> {
        return receiverRepository.getReceivers()
    }

    fun updateReceiverPriority(id: String, receiver: String, priority: String){
        return receiverRepository.updateReceiverPriority(id, receiver, priority);
    }
    fun deleteReceiver(id: String, receiver: String){
        return receiverRepository.deleteReceiver(id, receiver);
    }
}