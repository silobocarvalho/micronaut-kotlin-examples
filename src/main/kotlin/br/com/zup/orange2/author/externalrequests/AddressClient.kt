package br.com.zup.orange2.author.externalrequests

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("http://viacep.com.br/ws/")
open interface AddressClient {

    @Get(value="{zipcode}/json/")
    open fun requestAddress(zipcode: String) : HttpResponse<AddressResponse>


}