package topfarming.poc.infrastructure.configuration

import javax.validation.constraints.NotNull

interface ApplicationConfiguration {

    @get:NotNull
    val max: Int?
}