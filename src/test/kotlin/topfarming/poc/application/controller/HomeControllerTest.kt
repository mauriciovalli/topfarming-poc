package topfarming.poc.application.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class HomeControllerTest {
    @Inject
    @field:Client("/")
    lateinit var client : HttpClient

    @Test
    fun testHome() {
        val request: HttpRequest<Any> = HttpRequest.GET("/")
        val body = client.toBlocking().retrieve(request)
        assertNotNull(body)
        assertEquals("Hello Top Farming", body)
    }
}
