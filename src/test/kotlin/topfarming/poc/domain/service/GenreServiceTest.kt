package topfarming.poc.domain.service

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import topfarming.poc.domain.model.Genre
import topfarming.poc.domain.repository.GenreRepository
import java.util.*
import javax.inject.Inject
import kotlin.test.assertEquals


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
            Arrays.asList(Genre("DevOps"), Genre("Microservices"))
        //Arrays.asList(Genre.builder().label("one").build(), Genre.builder().label("two").build())

        Mockito.`when`(genreRepository.findAll()).thenReturn(mocks)

        val genres: List<Genre> = genreService.findAll()

        // Assert the response
        assertEquals(mocks, genres)

        verify(genreRepository).findAll()
    }
}