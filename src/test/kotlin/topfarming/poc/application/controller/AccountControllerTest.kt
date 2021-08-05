package topfarming.poc.application.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import topfarming.poc.domain.proto.AccountProto
import javax.inject.Inject

@MicronautTest
class AccountControllerTest {
    @Inject
    @field:Client("/")
    lateinit var client : HttpClient

    @Test
    fun testShow() {
        val accountBuilder = AccountProto.Account.newBuilder()
        accountBuilder.id = 1
        accountBuilder.number = "123"
        accountBuilder.customerId = 5
        val expectedAccount = accountBuilder.build()

        val request: HttpRequest<Any> = HttpRequest.GET("/account")
        val body = client.toBlocking().retrieve(request, AccountProto.Account::class.java)
        assertNotNull(body)
        assertEquals(expectedAccount, body)
    }
}