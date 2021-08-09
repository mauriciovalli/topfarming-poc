package topfarming.poc.infrastructure.repository

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.repository.GenreCacheRepository
import javax.inject.Inject

@MicronautTest(startApplication = false)
class GenreRedisRepositoryTest {
    @Inject
    private lateinit var genreRepository : GenreCacheRepository

    @Test
    fun testGenreCache() {
        val dto1 = GenreDto(id = 1, name = "DevOps")
        val dto2 = GenreDto(id = 2, name = "Microservices")

        genreRepository.save(dto1)
        var value = genreRepository.findById(1)
        assertEquals(dto1.name, value!!.name)

        var cacheKeys = genreRepository.keys()
        assertEquals(1, cacheKeys.size)
        assert(cacheKeys.contains(dto1.id))

        genreRepository.save(dto2)
        value = genreRepository.findById(2)
        assertEquals(dto2.name, value!!.name)

        cacheKeys = genreRepository.keys()
        assertEquals(2, cacheKeys.size)
        assertTrue(cacheKeys.contains(dto1.id))
        assertTrue(cacheKeys.contains(dto2.id))

        genreRepository.invalidateCache()
    }
}