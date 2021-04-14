package br.com.zup.orange2.author

import br.com.zup.orange2.author.dto.NewAuthorRequest
import br.com.zup.orange2.author.externalrequests.AddressClient
import br.com.zup.orange2.author.externalrequests.AddressResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject


@MicronautTest
internal class AddAuthorControllerTest{

    @field:Inject
    lateinit var addressClientMock: AddressClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `should add new author`() {

        val newAuthorRequest = NewAuthorRequest(name = "Sidartha Carvalho", email = "sid@zup.com.br", description = "This is a description of my Author", zipcode = "63907-004", number = "666" )

        val addressResponse = AddressResponse(street = "Rua dos Zuppers" , city = "Quixadá" , state = "Ceará")

        Mockito.`when`(addressClientMock.requestAddress(newAuthorRequest.zipcode)).thenReturn(HttpResponse.ok(addressResponse))

        val request = HttpRequest.POST("/authors", newAuthorRequest)
        val response = client.toBlocking().exchange(request, Any::class.java)

        Assertions.assertEquals(HttpStatus.CREATED, response.status)
        Assertions.assertTrue(response.headers.contains("Location"))
        Assertions.assertTrue(response.header("Location").matches("/authors/\\d".toRegex()))
    }

    @MockBean(AddressClient::class)
    fun addressMock(): AddressClient{
        return Mockito.mock(AddressClient::class.java)
    }

}