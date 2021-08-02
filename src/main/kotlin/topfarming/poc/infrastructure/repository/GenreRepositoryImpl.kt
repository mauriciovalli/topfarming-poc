package topfarming.poc.infrastructure.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import topfarming.poc.domain.dto.SortingAndOrderArguments
import topfarming.poc.domain.model.Genre
import topfarming.poc.domain.repository.GenreRepository
import javax.transaction.Transactional


@Transactional(Transactional.TxType.MANDATORY)
@Repository
interface GenreRepositoryImpl :  JpaRepository<Genre, Long>, GenreRepository {
//    @Transactional
//    override fun findAll(args: SortingAndOrderArguments): List<Genre> {
//        //TODO IMPLEMENTAR
//
////        var qlString = "SELECT g FROM Genre as g"
////        if (args.order != null && args.sort != null && GenreRepositoryOldImpl.VALID_PROPERTY_NAMES.contains(args.sort)) {
////            qlString += " ORDER BY g." + args.sort + " " + args.order.toLowerCase()
////        }
////        val query = entityManager.createQuery(qlString, Genre::class.java)
////        query.maxResults = if (args.max != null) args.max else applicationConfiguration.max!!
////        if (args.offset != null)
////            query.firstResult = args.offset
////
////        return query.resultList
//        val genres: Set<Genre> = emptySet()
//        return genres.toList()
//    }
}