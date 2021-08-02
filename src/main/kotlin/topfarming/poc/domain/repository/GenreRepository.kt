package topfarming.poc.domain.repository


import io.micronaut.core.annotation.NonNull
import topfarming.poc.domain.dto.SortingAndOrderArguments
import topfarming.poc.domain.model.Genre
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

interface GenreRepository {
    /**
     * Returns all instances of the type.
     */
    @NonNull
    fun findAll(): List<Genre>

    /**
     * Retrieves an entity by its id.
     */
    @NonNull
    fun findById(@NonNull id: @NotNull Long): Optional<Genre>

    /**
     * Returns all instances of the type.
     */
    //TODO IMPLEMENTAR
//    fun findAll(@NonNull args: SortingAndOrderArguments): List<Genre>

    /**
     * Saves the given valid entity, returning a possibly new entity representing the saved state. Note that certain implementations may not be able to detect whether a save or update should be performed and may always perform an insert. The [.update] method can be used in this case to explicitly request an update.
     */
    @NonNull
    fun save(@NonNull entity: @Valid @NotNull Genre): Genre

    /**
     * This method issues an explicit update for the given entity. The method differs from [.save] in that an update will be generated regardless if the entity has been saved previously or not. If the entity has no assigned ID then an exception will be thrown.
     * */
    @NonNull
    fun update(@NonNull entity: @Valid @NotNull Genre): Genre

    /**
     * Deletes the entity with the given id.
     */
    fun deleteById(@NonNull id: @NotNull Long)
}