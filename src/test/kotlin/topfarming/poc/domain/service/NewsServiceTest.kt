package topfarming.poc.domain.service

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.Timeout
import java.time.Month
import javax.inject.Inject

@TestMethodOrder(OrderAnnotation::class)
@MicronautTest(startApplication = false) 
internal class NewsServiceTest {
    @Inject
    lateinit var newsService : NewsService

    @Timeout(4)
    @Test
    @Order(1)
    fun firstInvocationOfNovemberDoesNotHitCache() {
        val headlines = newsService.headlines(Month.NOVEMBER)
        assertEquals(2, headlines!!.size)
    }

    @Timeout(1)
    @Test
    @Order(2)
    fun secondInvocationOfNovemberHitsCache() {
        val headlines = newsService.headlines(Month.NOVEMBER)
        assertEquals(2, headlines!!.size)
    }

    @Timeout(4)
    @Test
    @Order(3)
    fun firstInvocationOfOctoberDoesNotHitCache() {
        val headlines = newsService.headlines(Month.OCTOBER)
        assertEquals(1, headlines!!.size)
    }

    @Timeout(1)
    @Test
    @Order(4)
    fun secondInvocationOfOctoberHitsCache() {
        val headlines = newsService.headlines(Month.OCTOBER)
        assertEquals(1, headlines!!.size)
    }

    @Timeout(1)
    @Test
    @Order(5)
    fun addingAHeadlineToNovemberUpdatesCache() {
        val headlines = newsService.addHeadline(Month.NOVEMBER, "Micronaut 1.3 Milestone 1 Released")
        assertEquals(3, headlines!!.size)
    }

    @Timeout(1)
    @Test
    @Order(6)
    fun novemberCacheWasUpdatedByCachePutAndThusTheValueIsRetrievedFromTheCache() {
        val headlines = newsService.headlines(Month.NOVEMBER)
        assertEquals(3, headlines!!.size)
    }

    @Timeout(1)
    @Test
    @Order(7)
    fun invalidateNovemberCacheWithCacheInvalidate() {
        assertDoesNotThrow { newsService.removeHeadline(Month.NOVEMBER, "Micronaut 1.3 Milestone 1 Released") }
    }

    @Timeout(1)
    @Test
    @Order(8)
    fun octoberCacheIsStillValid() {
        val headlines = newsService.headlines(Month.OCTOBER)
        assertEquals(1, headlines!!.size)
    }

    @Timeout(4)
    @Test
    @Order(9)
    fun novemberCacheWasInvalidated() {
        val headlines = newsService.headlines(Month.NOVEMBER)
        assertEquals(2, headlines!!.size)
    }
}
