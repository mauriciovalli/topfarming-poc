package topfarming.poc.domain.dto

import io.micronaut.core.annotation.Introspected
import java.time.Month

@Introspected
data class NewsDto(val month: Month, val headlines: List<String>)