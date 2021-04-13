package br.com.zup.orange2

class DetailsAuthorResponse(author: Author) {

    val nome = author.name
    val email = author.email
    val description = author.description
}
