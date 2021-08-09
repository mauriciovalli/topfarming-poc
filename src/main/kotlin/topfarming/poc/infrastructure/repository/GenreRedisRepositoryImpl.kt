package topfarming.poc.infrastructure.repository

import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.repository.GenreCacheRepository
import javax.inject.Singleton

@Singleton
class GenreRedisRepositoryImpl : EntityRedisRepository<GenreDto>(), GenreCacheRepository {
    override fun findById(id: Long): GenreDto? {
        return getValue(id.toString())
    }

    override fun save(dto: GenreDto) {
        setValue(dto.id.toString(),dto)
    }

    override fun keys(): List<Long> {
        return getKeys().map { t -> t.toLong() }
    }
}