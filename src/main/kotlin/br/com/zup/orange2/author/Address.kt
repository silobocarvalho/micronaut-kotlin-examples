package br.com.zup.orange2.author

import br.com.zup.orange2.author.externalrequests.AddressResponse
import javax.persistence.Embeddable

@Embeddable
open class Address(addressResponse: AddressResponse, number: String) {

    val street = addressResponse.street
    val city = addressResponse.city
    val state = addressResponse.state
    val number = number

}
