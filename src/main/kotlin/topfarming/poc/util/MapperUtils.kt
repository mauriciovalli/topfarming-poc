package topfarming.poc.domain.dto

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies

object MapperUtils {
    val mapper = MyModelMapper()

    inline fun <S, reified T> convert(source: S): T = mapper.map(source, T::class.java)
}

class MyModelMapper() : ModelMapper() {
    init {
        configuration.matchingStrategy = MatchingStrategies.LOOSE
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isSkipNullEnabled = true
    }
}

