package topfarming.poc.infrastructure.repository

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import topfarming.poc.domain.model.Genre
import java.util.*
import javax.inject.Inject
import kotlin.test.assertEquals

@MicronautTest
class GenreJpaRepositoryTest {

    @Inject
    private lateinit var genreRepository: GenreRepositoryImpl

    @Test
    fun testFindAll() {
        val mocks: List<Genre> =
            Arrays.asList(Genre("DevOps"), Genre("Microservices"))
            //Arrays.asList(Genre.builder().label("one").build(), Genre.builder().label("two").build())
        genreRepository.saveAll(mocks)
        val genres: List<Genre> = genreRepository.findAll()

        // Assert the response
        assertEquals(mocks, genres)
    }
}