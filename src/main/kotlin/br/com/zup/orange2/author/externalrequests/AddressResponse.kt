package br.com.zup.orange2.author.externalrequests

import com.fasterxml.jackson.annotation.JsonProperty

data class AddressResponse(
    @JsonProperty(value = "logradouro")
    val street: String,
    @JsonProperty(value = "localidade")
    val city: String,
    @JsonProperty(value = "uf")
    val state: String)
