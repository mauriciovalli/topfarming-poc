package topfarming.poc.domain.service

import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.dto.SortingAndOrderArguments
import topfarming.poc.domain.model.Genre
import topfarming.poc.domain.repository.GenreRepository
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
        if(optional.isPresent) return GenreDto.of(optional.get())
        else return null
    }

    override fun save(dto: GenreDto): GenreDto {
        val genre = GenreDto.convertToEntity(dto)
        return GenreDto.of(genreRepository.save(genre))
    }

    override fun deleteById(id: Long) {
        genreRepository.deleteById(id)
    }

    override fun findAll(): List<Genre> {
        return genreRepository.findAll()
    }

    override fun findAll(args: SortingAndOrderArguments): List<Genre> {
        //TODO IMPLEMENTAR
        //return genreRepository.findAll(args)
        val genres: Set<Genre> = emptySet()
        return genres.toList()
    }

    override fun update(dto: GenreDto): Int {
        val optional = genreRepository.findById(dto.id)
        if (optional.isPresent) {
            genreRepository.update(GenreDto.convertToEntity(dto))
            return 1
        } else return 0
    }
}