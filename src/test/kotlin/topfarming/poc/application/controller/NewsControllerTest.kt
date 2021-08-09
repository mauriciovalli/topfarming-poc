package topfarming.poc.application.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import topfarming.poc.domain.dto.NewsDto
import java.time.Month
import javax.inject.Inject

@MicronautTest
class NewsControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Timeout(10)
    @Test
    fun fetchingOctoberHeadlinesUsesCache() {
        val request: HttpRequest<Any> = HttpRequest.GET(UriBuilder.of("/news/").path(Month.OCTOBER.name).build())
        var news: NewsDto = client.toBlocking().retrieve(request, NewsDto::class.java)
        val expected = "Micronaut AOP: Awesome flexibility without the complexity"
        assertEquals(listOf(expected), news.headlines)
        news = client.toBlocking().retrieve(request, NewsDto::class.java)
        assertEquals(listOf(expected), news.headlines)
    }
}
