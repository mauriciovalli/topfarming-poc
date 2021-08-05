package topfarming.poc.infrastructure.repository

import topfarming.poc.domain.model.Genre
import javax.inject.Singleton

@Singleton
class GenreRedisRepositoryImpl : EntityRedisRepository<Genre>() {

}