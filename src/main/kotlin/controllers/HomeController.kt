package controllers

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller
class HomeController {
    @Get("/")
    @Produces(MediaType.TEXT_PLAIN)
    fun index() = "Hello World"

    @Get("/naye")
    @Produces(MediaType.TEXT_PLAIN)
    fun printMyName() = "Hello, I'm Naye!"
}