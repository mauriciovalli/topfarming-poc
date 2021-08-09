package topfarming.poc.application.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import topfarming.poc.domain.dto.NewsDto
import topfarming.poc.domain.service.NewsService
import java.time.Month

@Controller("news")
class NewsController(val newsService: NewsService) {
    @Get("/{month}")
    fun index(month: Month): NewsDto {
        return NewsDto(month, newsService.headlines(month).orEmpty())
    }
}
