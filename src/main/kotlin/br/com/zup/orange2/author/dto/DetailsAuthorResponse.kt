package br.com.zup.orange2.author.dto

import br.com.zup.orange2.author.Author

class DetailsAuthorResponse(author: Author) {

    val nome = author.name
    val email = author.email
    val description = author.description
}
