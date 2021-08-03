package topfarming.poc.application.controller

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.protobuf.codec.ProtobufferCodec
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.proto.GenreProto
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@MicronautTest
internal class GenreControllerTest {
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun testFindNonExistingGenreReturns404() {
        //"find non existing genre returns 404"
        val exception = assertFailsWith(HttpClientResponseException::class) {
            client.toBlocking().exchange<Any, Any>(HttpRequest.GET("/genres/99"))
        }
        assertEquals(HttpStatus.NOT_FOUND, exception.response.status)
    }


    @Test
    fun testGenreCrudOperations() {

        val genreIds = ArrayList<Long?>()

        var request: HttpRequest<Any> = HttpRequest.POST("/genres", GenreDto("DevOps"))
        var response: HttpResponse<Any> = client.toBlocking().exchange(request)
        genreIds.add(entityId(response))

        assertEquals(HttpStatus.CREATED, response.status)

        request = HttpRequest.POST("/genres", GenreDto("Microservices"))
        response = client.toBlocking().exchange(request)

        assertEquals(HttpStatus.CREATED, response.status)

        val id = entityId(response)!!
        genreIds.add(id)
        request = HttpRequest.GET("/genres/$id")

        val genre = client.toBlocking().retrieve(request, GenreDto::class.java)
        assertEquals("Microservices", genre.name)

        request = HttpRequest.GET("/genres/list")
        val genres = client.toBlocking()
            .retrieve(request, Argument.of(List::class.java, GenreDto::class.java)) //as List<GenreDto>
        assertEquals(2, genres.size)

        // cleanup:
        for (genreId in genreIds) {
            request = HttpRequest.DELETE("/genres/" + genreId!!)
            response = client.toBlocking().exchange(request)
            assertEquals(HttpStatus.NO_CONTENT, response.status)
        }
    }

    @Test
    fun testGenreProtobuf() {
        var request: HttpRequest<Any> = HttpRequest.POST("/genres", GenreDto("DevOps"))
        var response: HttpResponse<Any> = client.toBlocking().exchange(request)
        val id = entityId(response)!!

        request = HttpRequest.GET("/genres/$id")
        val genre = client.toBlocking().retrieve(request, GenreDto::class.java)
        assertEquals("DevOps", genre.name)

        val genreProto = client.toBlocking()
            .retrieve(request.header(ACCEPT, ProtobufferCodec.PROTOBUFFER_ENCODED), GenreProto.Genre::class.java)
        assertEquals("DevOps", genreProto.name)

        request = HttpRequest.DELETE("/genres/$id")
        response = client.toBlocking().exchange(request)
        assertEquals(HttpStatus.NO_CONTENT, response.status)
    }

    private fun entityId(response: HttpResponse<*>): Long? {
        val path = "/genres/"
        val value = response.header(HttpHeaders.LOCATION) ?: return null
        val index = value.indexOf(path)
        return if (index != -1) {
            java.lang.Long.valueOf(value.substring(index + path.length))
        } else null
    }

    @AfterEach
    fun afterGroup() {
        client.close()
    }
}