package topfarming.poc.domain.service

import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.dto.SortingAndOrderArguments
import topfarming.poc.domain.model.Genre
import javax.validation.constraints.NotBlank

interface GenreService {
    fun findById(id: Long): GenreDto?

    fun save(dto: GenreDto): GenreDto

    fun deleteById(id: Long)

    fun findAll(): List<GenreDto>

    fun findAll(args: SortingAndOrderArguments): List<GenreDto>

    fun update(dto: GenreDto): Int
}