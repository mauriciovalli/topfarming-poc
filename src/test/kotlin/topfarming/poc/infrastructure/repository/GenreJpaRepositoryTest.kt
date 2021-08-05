package topfarming.poc.infrastructure.repository

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import topfarming.poc.domain.model.Genre
import javax.inject.Inject

@MicronautTest
class GenreJpaRepositoryTest {

    @Inject
    private lateinit var genreRepository: GenreJpaRepositoryImpl

    @Test
    fun testFindAll() {
        val mocks = listOf(Genre(name = "DevOps"), Genre(name = "Microservices"))
        genreRepository.saveAll(mocks)
        val genres: List<Genre> = genreRepository.findAll()

        // Assert the response
        assertEquals(mocks, genres)
    }
}