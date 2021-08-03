package topfarming.poc.domain.service

import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.dto.GenreDto.Companion.of
import topfarming.poc.domain.dto.SortingAndOrderArguments
import topfarming.poc.domain.model.Genre
import topfarming.poc.domain.repository.GenreRepository
import java.util.stream.Collectors
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Transactional
@Singleton
open class GenreServiceImpl : GenreService {
    @Inject
    private lateinit var genreRepository: GenreRepository

    override fun findById(id: Long): GenreDto? {
        val optional = genreRepository.findById(id)
        return if(optional.isPresent) of(optional.get())
        else null
    }

    override fun save(dto: GenreDto): GenreDto {
        val genre = GenreDto.convertToEntity(dto)
        return of(genreRepository.save(genre))
    }

    override fun deleteById(id: Long) {
        genreRepository.deleteById(id)
    }

    override fun findAll(): List<GenreDto> {
        val list: List<Genre> = genreRepository.findAll()
        return list.stream().map { genre: Genre -> of(genre) }.collect(Collectors.toList())
    }

    override fun findAll(args: SortingAndOrderArguments): List<GenreDto> {
        //TODO IMPLEMENTAR
        //return genreRepository.findAll(args)
        val genres: Set<GenreDto> = emptySet()
        return genres.toList()
    }

    override fun update(dto: GenreDto): Int {
        val optional = genreRepository.findById(dto.id)
        return if (optional.isPresent) {
            genreRepository.update(GenreDto.convertToEntity(dto))
            1
        } else 0
    }
}