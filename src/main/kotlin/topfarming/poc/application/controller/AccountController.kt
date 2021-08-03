package topfarming.poc.application.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.protobuf.codec.ProtobufferCodec
import topfarming.poc.domain.proto.AccountProto

@Controller("/account")
class AccountController {
    @Get("/", produces = [ProtobufferCodec.PROTOBUFFER_ENCODED])
    fun show(): AccountProto.Account {
        val accountBuilder = AccountProto.Account.newBuilder()
        accountBuilder.id = 1
        accountBuilder.number = "123"
        accountBuilder.customerId = 5
        return accountBuilder.build()
    }
}