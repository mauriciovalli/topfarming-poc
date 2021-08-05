package topfarming.poc.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

object JsonUtils {
    @Throws(JsonProcessingException::class)
    fun mapToJson(obj: Any?): String? {
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(obj)
    }

    @Throws(JsonProcessingException::class)
    fun <T> mapFromJson(json: String?, clazz: Class<T>?): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(json, clazz)
    }

    @Throws(JsonProcessingException::class)
    fun <T> mapFromJson(json: String?, valueTypeRef: TypeReference<T>?): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(json, valueTypeRef)
    }
}