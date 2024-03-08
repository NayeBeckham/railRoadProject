package controller

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import models.DestinationModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doReturn
import services.DestinationService
import java.util.*

@MicronautTest
class DestinationControllerTest {

    @Inject
    lateinit var destinationService: DestinationService

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @MockBean(DestinationService::class)
    fun destinationServiceMock(): DestinationService? {
        return Mockito.mock(DestinationService::class.java)
    }

    @BeforeEach
    fun setMocks(){
        doReturn(
            listOf(
                DestinationModel(UUID.randomUUID(), "LA", 2),
                DestinationModel(UUID.randomUUID(), "Chicago", 1)
            )
        ). `when` (destinationServiceMock())?.getDestinations()


        val destination = DestinationModel(UUID.randomUUID(), "Houston", 3)

        doNothing().`when`(destinationServiceMock())?.createDestination(destination)

        doNothing(
        ). `when` (destinationServiceMock())?.deleteDestination("1", "Houston")

        doNothing(
        ). `when` (destinationServiceMock())?.updateDestinationPriority("1", "Houston", "2")

    }

    @Test
    fun `GET destination list successfully`() {
        val request = HttpRequest.GET<List<DestinationModel>>("/destination")
            .contentType(MediaType.APPLICATION_JSON)
        val response = client.toBlocking().exchange(request, Argument.listOf(DestinationModel::class.java))

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.OK, response.status)
        Assertions.assertEquals(destinationService.getDestinations(), response.body())
    }


    @Test
    fun `POST creates destination successfully`() {
        val request = HttpRequest.POST("/destination", DestinationModel(UUID.randomUUID(), "Houston", 2))
        val response = client.toBlocking().exchange(request, String::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.OK, response.status)
        Assertions.assertEquals("Destination created successfully", response.body())
    }

    @Test
    fun `DELETE deletes destination successfully`() {
        val request = HttpRequest.DELETE("/destination/1/Chicago",null)
        val response = client.toBlocking().exchange(request, String::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.OK, response.status)
        Assertions.assertEquals("Destination deleted successfully", response.body())
    }

    @Test
    fun `PUT updated destination priority successfully`() {
        val request = HttpRequest.PUT("/destination/1/Chicago/2",null)
        val response = client.toBlocking().exchange(request, String::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.OK, response.status)
        Assertions.assertEquals("Destination priority updated successfully", response.body())
    }
}