package controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import models.DestinationModel
import services.DestinationService
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

@Controller("/destination")
class DestinationController(private val destinationService: DestinationService) {
    @Get("/")
    @Status(HttpStatus.OK)
    @Produces(MediaType.APPLICATION_JSON)
    fun getDestinations(): List<DestinationModel> {
        return destinationService.getDestinations()
    }

    @Post("/create")
    @Status(HttpStatus.OK)
    fun createDestination(@Body destination: DestinationModel): MutableHttpResponse<String>? {
        destinationService.createDestination(destination)
        return ok()
    }

    @Put("/{id}/{cityName}/update/{newCityName}")
    @Status(HttpStatus.OK)
    fun updateDestinationPriority(@PathVariable id: String, @PathVariable cityName: String, @PathVariable newCityName: String){
        destinationService.updateDestinationPriority(id, cityName, newCityName);
    }

    @Delete("/delete/{id}/{priority}")
    @Status(HttpStatus.OK)
    fun deleteDestination(@PathVariable id: String, @PathVariable priority: String): MutableHttpResponse<String>?{
        destinationService.deleteDestination(id, priority);
        return ok()
    }

}