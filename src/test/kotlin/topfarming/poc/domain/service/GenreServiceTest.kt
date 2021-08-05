package topfarming.poc.domain.service

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.model.Genre
import topfarming.poc.domain.repository.GenreRepository
import java.util.*
import javax.inject.Inject


@MicronautTest
class GenreServiceTest {
    @MockBean(GenreRepository::class)
    fun genreRepository(): GenreRepository? {
        return mock(GenreRepository::class.java)
    }

    @Inject
    private lateinit var genreRepository: GenreRepository

    @Inject
    private lateinit var genreService: GenreServiceImpl

    @Test
    fun testFindAll() {
        val mocks: List<Genre> =
            Arrays.asList(Genre(name = "DevOps"), Genre(name = "Microservices"))

        Mockito.`when`(genreRepository.findAll()).thenReturn(mocks)

        val genres: List<GenreDto> = genreService.findAll()

        // Assert the response
        assertEquals(mocks.get(0).name, genres.get(0).name)
        assertEquals(mocks.get(1).name, genres.get(1).name)

        verify(genreRepository).findAll()
    }
}