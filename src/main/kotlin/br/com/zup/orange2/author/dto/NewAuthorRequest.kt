package br.com.zup.orange2.author.dto


import br.com.zup.orange2.author.Address
import br.com.zup.orange2.author.externalrequests.AddressResponse
import br.com.zup.orange2.author.Author
import br.com.zup.orange2.author.validation.Zipcode

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NewAuthorRequest(
    @field:NotBlank val name: String,
    @field:NotBlank @field:Email   val email: String,
    @field:NotBlank @field:Size(max=400) val description: String,
    @field:NotBlank @field:Zipcode val zipcode: String,
    @field:NotBlank val number: String
    ) {
    fun toModel(addressResponse: AddressResponse): Author {
        val address = Address(addressResponse, number)
        return Author(this.name, this.email, this.description, address)
    }
}