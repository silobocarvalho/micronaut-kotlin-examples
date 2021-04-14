package br.com.zup.orange2.author.externalrequests

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("http://viacep.com.br/ws/")
interface AddressClient {

    @Get(value="{zipcode}/json/")
    fun requestAddress(zipcode: String) : HttpResponse<AddressResponse>


}