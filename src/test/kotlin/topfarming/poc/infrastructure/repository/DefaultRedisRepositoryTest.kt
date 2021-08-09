package topfarming.poc.infrastructure.repository

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.repository.GenreCacheRepository
import javax.inject.Inject

@MicronautTest(startApplication = false)
class DefaultRedisRepositoryTest {
    @Inject
    private lateinit var defaultRedisRepository : DefaultRedisRepository

    @Test
    fun testCache() {
        val key = "key"
        val value = "myValue"
        defaultRedisRepository.setValue(key, value)
        val cacheValue = defaultRedisRepository.getValue(key)
        assertEquals(value, cacheValue)

        val cacheKeys = defaultRedisRepository.getKeys()
        assert(cacheKeys.contains(key))

        defaultRedisRepository.invalidateCache()
    }
}