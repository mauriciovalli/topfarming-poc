package topfarming.poc.application.controller

import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/message", produces = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
class MessageController {
    @Get("/")
    fun sample(headers: HttpHeaders): HttpResponse<*> {
        // Simple object to be returned from this method either
        // as XML or JSON, based on the HTTP Accept header.
        val message = Message("Micronaut is awesome")

        // Check if HTTP Accept header is "application/xml".
        if (headerAcceptXml(headers)) {
            // Encode messages as XML.
            val xml = encodeAsXml(message)

            // Return response and set content type
            // to "application/xml".
            return HttpResponse.ok(xml)
                    .contentType(MediaType.APPLICATION_XML_TYPE)
        }

        // Default response as JSON.
        return HttpResponse.ok(message)
    }

    /**
     * Check HTTP Accept header with value "application/xml"
     * is sent by the client.
     *
     * @param headers Http headers sent by the client.
     * @return True if the Accept header contains "application/xml".
     */
    private fun headerAcceptXml(headers: HttpHeaders): Boolean {
        return headers.accept().contains(MediaType.APPLICATION_XML_TYPE)
    }

    /**
     * Very simple way to create XML String with message content.
     *
     * @param message Message to be encoded as XML String.
     * @return XML String with message content.
     */
    private fun encodeAsXml(message: Message): String {
        return String.format("<content>%s</content>", message.content)
    }
}

class Message(val content: String)