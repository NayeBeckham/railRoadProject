package controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import models.DestinationModel
import services.DestinationService

@Controller("/destination")
class DestinationController(private val destinationService: DestinationService) {
    @Get("/")
    @Status(HttpStatus.OK)
    @Produces(MediaType.APPLICATION_JSON)
    fun getDestinations(): HttpResponse<List<DestinationModel>> {
        return ok( destinationService.getDestinations() )
    }

    @Post("/")
    @Status(HttpStatus.OK)
    fun createDestination(@Body destination: DestinationModel): HttpResponse<String> {
        if(destination.city.isEmpty() || destination.priority == -1)
            return badRequest("Values can't be empty or null")

        destinationService.createDestination(destination)
        return ok("Destination created successfully")
    }

    @Put("/{id}/{cityName}/{priority}")
    @Status(HttpStatus.OK)
    fun updatePriority(
        @PathVariable id: String,
        @PathVariable cityName: String,
        @PathVariable priority: String) : HttpResponse<String> {
        if(cityName.isEmpty() || priority.isEmpty() || id.isEmpty())
            return badRequest("Values can't be empty or null")
        destinationService.updateDestinationPriority(id, cityName, priority);
        return ok("Destination priority updated successfully")
    }

    @Delete("/{id}/{cityName}")
    @Status(HttpStatus.OK)
    fun deleteDestination(@PathVariable id: String, @PathVariable cityName: String): HttpResponse<String> {
        if(id.isEmpty() || cityName.isEmpty())
            return badRequest("Values can't be empty or null")
        destinationService.deleteDestination(id, cityName)
        return ok("Destination deleted successfully")
    }

}