package topfarming.poc.application.rest

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.validation.Validated
import topfarming.poc.domain.proto.AccountProto


@Validated
@Controller("/account")
class AccountControler {

    @Get("/", produces = ["application/x-protobuf"])
    fun show(): AccountProto.Account {
        val accountBuilder = AccountProto.Account.newBuilder()
        accountBuilder.id = 1
        accountBuilder.number = "123"
        accountBuilder.customerId = 5
        return accountBuilder.build()
    }
}