package topfarming.poc.domain.repository

import io.micronaut.core.annotation.NonNull
import topfarming.poc.domain.dto.GenreDto
import javax.validation.Valid
import javax.validation.constraints.NotNull

interface GenreCacheRepository {
    /**
     * Retrieves an dto by its id.
     */
    @NonNull
    fun findById(@NonNull id: @NotNull Long) : GenreDto?

    /**
     * Saves the given valid dto.
     */
    @NonNull
    fun save(@NonNull dto: @Valid @NotNull GenreDto)

    /**
     * Invalidate Cache.
     */
    fun invalidateCache()

    /**
     * Retrieves store keys.
     */
    fun keys(): List<Long>
}