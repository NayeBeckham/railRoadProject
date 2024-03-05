package controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import models.ReceiverModel
import repositories.ReceiverRepository

@Controller("/receiver")
class ReceiverController(private val receiverRepository: ReceiverRepository) {

    @Get("/")
    @Status(HttpStatus.OK)
    @Produces(MediaType.APPLICATION_JSON)
    fun getReceivers(): HttpResponse<List<ReceiverModel>> {
        return HttpResponse.ok(receiverRepository.getReceivers())
    }

    @Post("/")
    @Status(HttpStatus.OK)
    fun createReceiver(@Body receiver: ReceiverModel): HttpResponse<String> {
        if(receiver.receiver.isEmpty() || receiver.priority == -1)
            return HttpResponse.badRequest("Values can't be empty or null")

        receiverRepository.createReceiver(receiver)
        return HttpResponse.ok("Receiver created successfully")
    }

    @Put("/{id}/{receiver}/{priority}")
    @Status(HttpStatus.OK)
    fun updatePriority(
        @PathVariable id: String,
        @PathVariable receiver: String,
        @PathVariable priority: String) : HttpResponse<String> {
        if(receiver.isEmpty() || priority.isEmpty() || id.isEmpty())
            return HttpResponse.badRequest("Values can't be empty or null")
        receiverRepository.updateReceiverPriority(id, receiver, priority);
        return HttpResponse.ok("Receiver priority updated successfully")
    }

    @Delete("/{id}/{receiver}")
    @Status(HttpStatus.OK)
    fun deleteDestination(@PathVariable id: String, @PathVariable receiver: String): HttpResponse<String> {
        if(id.isEmpty() || receiver.isEmpty())
            return HttpResponse.badRequest("Values can't be empty or null")
        receiverRepository.deleteReceiver(id, receiver)
        return HttpResponse.ok("Receiver deleted successfully")
    }

}