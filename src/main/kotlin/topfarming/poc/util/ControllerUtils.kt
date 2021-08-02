package topfarming.poc.util

import io.micronaut.http.HttpHeaders
import io.micronaut.http.MediaType

object ControllerUtils {
    /**
     * Check HTTP Accept header with value "application/x-protobuf"
     * is sent by the client.
     *
     * @param headers Http headers sent by the client.
     * @return True if the Accept header contains "application/x-protobuf".
     */
    fun headerAcceptProto(headers: HttpHeaders): Boolean {
        return headers.accept().contains(MediaType("application/x-protobuf"))
    }
}