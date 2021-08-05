package topfarming.poc.infrastructure.repository

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import topfarming.poc.domain.model.Genre
import topfarming.poc.infrastructure.repository.DefaultRedisRepository
import topfarming.poc.infrastructure.repository.GenreRedisRepositoryImpl
import javax.inject.Inject

@MicronautTest
class RedisRepositoryTest {
    @Inject
    private lateinit var genreRepository : GenreRedisRepositoryImpl
    @Inject
    private lateinit var defaultRedisRepository : DefaultRedisRepository

    @Test
    fun testCache() {
        defaultRedisRepository.setValue("key", "myValue")
        genreRepository.setValue("key", Genre(name="DevOps"))
        val value = defaultRedisRepository.getValue("key")
        assertEquals("myValue", value)

        defaultRedisRepository.getClean()
        genreRepository.getClean()
    }

    @Test
    fun testGenreCache() {
        genreRepository.setValue("1", Genre(name="DevOps"))
        val value :Genre = genreRepository.getValue("1")
        assertEquals("DevOps", value.name)

        genreRepository.getClean()
    }
}