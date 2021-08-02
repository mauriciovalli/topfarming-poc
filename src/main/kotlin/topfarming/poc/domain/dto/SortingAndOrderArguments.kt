package topfarming.poc.domain.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@Introspected
data class SortingAndOrderArguments(
    @field:PositiveOrZero
    val offset: Int? = null,

    @field:Positive
    val max: Int? = null,

    @field:Pattern(regexp = "id|name")
    val sort: String? = null,

    @field:Pattern(regexp = "asc|ASC|desc|DESC")
    val order: String? = null
)