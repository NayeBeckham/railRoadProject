package controllers

import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.serde.annotation.Serdeable
import models.RailRoadModel
import models.RailRoadModelView

import services.RailRoadService

@Controller("/railsRoad")
class RailRoadController(
    private val railRoadService: RailRoadService
) {
    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun index() = "Rails Road Controller Index";

    @Get("/getSortedRoad")
    @Status(HttpStatus.OK)
    @Produces(MediaType.TEXT_JSON)
    fun getSortedRoad(): List<RailRoadModelView> {
    return railRoadService.getRailsRoadOrder();
    }

    @Post("/createRailOp")
    @Status(HttpStatus.OK)
    fun createRailOperation(@Body railList: List<RailRoadModelView>): List<RailRoadModelView> {
        return railRoadService.createRailsRoad(railList);
    }

}