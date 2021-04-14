package br.com.zup.orange2.author

import br.com.zup.orange2.author.dto.DetailsAuthorResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject
import br.com.zup.orange2.author.externalrequests.AddressResponse
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import br.com.zup.orange2.author.Address

@MicronautTest
internal class AuthorControllerTest{

    @field:Inject
    lateinit var authorRespository : AuthorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var author: Author

    @BeforeEach
    internal fun setUp() {
        val addressResponse = AddressResponse(street = "Rua Maria das Dores", city = "Quixadá", state = "Ceará")
        val address = Address(addressResponse = addressResponse, number = "123")
        author = Author(name = "Sidartha Carvalho", email = "sid@zup.com.br", description = "this is a real description", address = address)

        authorRespository.save(author)
    }

    @AfterEach
    internal fun tearDown() {
        authorRespository.deleteAll()
    }

    @Test
    internal fun `should return author details`(){

        val response = client.toBlocking().exchange("/authors?email=${author.email}", DetailsAuthorResponse::class.java)

        Assertions.assertEquals(HttpStatus.OK, response.status)
        Assertions.assertNotNull(response.body())
        Assertions.assertEquals(author.name, response.body().name)
        Assertions.assertEquals(author.email, response.body().email)
        Assertions.assertEquals(author.description, response.body().description)
    }
}