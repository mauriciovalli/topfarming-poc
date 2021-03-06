package topfarming.poc.application.controller

import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.protobuf.codec.ProtobufferCodec
import io.micronaut.validation.Validated
import topfarming.poc.domain.dto.GenreDto
import topfarming.poc.domain.dto.MapperUtils
import topfarming.poc.domain.dto.SortingAndOrderArguments
import topfarming.poc.domain.model.Genre
import topfarming.poc.domain.proto.GenreProto
import topfarming.poc.domain.service.GenreService
import topfarming.poc.util.ControllerUtils.headerAcceptProto
import java.net.URI
import javax.inject.Inject
import javax.validation.Valid


@Validated
@Controller("/genres")
class GenreController {

    @Inject
    protected lateinit var genreService: GenreService

    @Get("/{id}", produces = [MediaType.APPLICATION_JSON, ProtobufferCodec.PROTOBUFFER_ENCODED])
    fun show(id: Long, headers: HttpHeaders): HttpResponse<*> {
        val genreDto = genreService.findById(id)

        // Check if HTTP Accept header is "application/x-protobuf".
        if (headerAcceptProto(headers)) {
          if(genreDto!=null) {
              val genreProto: GenreProto.Genre = MapperUtils.convert(genreDto)
              return HttpResponse.ok(genreProto)
          } else {
              return HttpResponse.notFound<GenreProto.Genre>()
          }
        }

        // Default response as JSON.
        return if(genreDto!=null) HttpResponse.ok(genreDto) else HttpResponse.notFound<GenreDto>()
    }

    @Get("/list")
    fun list(): List<GenreDto> {
        return genreService.findAll()
    }

    @Get("/listSort{?args*}")
    fun list(@Valid args: SortingAndOrderArguments): List<GenreDto> {
        return genreService.findAll(args)
    }

    @Put("/")
    fun update(@Body @Valid dto: GenreDto): HttpResponse<Any> {
        genreService.update(dto)
        return HttpResponse
                .noContent<Any>()
                .header(HttpHeaders.LOCATION, location(dto.id).path)
    }

    @Post("/")
    fun save(@Body @Valid dto: GenreDto): HttpResponse<GenreDto> {
        val genre = genreService.save(dto)
        return HttpResponse
                .created(genre)
                .headers { headers -> headers.location(location(genre)) }
    }

    @Delete("/{id}")
    fun delete(id: Long): HttpResponse<Any> {
        genreService.deleteById(id)
        return HttpResponse.noContent()
    }

    protected fun location(id: Long?): URI {
        return URI.create("/genres/" + id!!)
    }

    protected fun location(genre: GenreDto): URI {
        return location(genre.id)
    }
}