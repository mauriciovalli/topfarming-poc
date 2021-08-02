package topfarming.poc.domain.dto

import io.micronaut.core.annotation.Introspected
import topfarming.poc.domain.model.Genre
import javax.validation.constraints.NotBlank

@Introspected
data class GenreDto(
        val id: Long,

        @NotBlank
        val name: String
) {
    constructor(name: String) : this(0, name)

    constructor() : this(0, "")

    companion object {
        @JvmStatic
        fun of(model: Genre): GenreDto {
            // Map or transform another field like dates
            return MapperUtils.convert(model)
        }

        @JvmStatic
        fun convertToEntity(dto: GenreDto): Genre {
            // Map or transform another field like dates
            return MapperUtils.convert(dto)
        }
    }


}