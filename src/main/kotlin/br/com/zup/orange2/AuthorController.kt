package br.com.zup.orange2



import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid

@Controller(value = "/authors")
@Validated
class AuthorController(val authorRepository: AuthorRepository) {

    @Post
    @Transactional
    fun addAuthor(@Body @Valid request: NewAuthorRequest){
        println(request)
        val newAuthor = request.toModel()

        //println("Author name: ${newAuthor.name}")

        authorRepository.save(newAuthor)

        //println("Author cadastrado no banco: ${authorRepository.findById(1).get().name}")

    }

    @Get
    @Transactional
    fun listAuthors(@QueryValue(defaultValue = "") email: String) : HttpResponse<Any>{

        if(email.isBlank()){
            val authors = authorRepository.findAll()
            val response = authors.map { author -> DetailsAuthorResponse(author) }
            return HttpResponse.ok(response)
        }

        val authorFromDbOptional = authorRepository.findByEmail(email)

        if(authorFromDbOptional.isEmpty){
            return HttpResponse.notFound()
        }

        val authorFromDb = authorFromDbOptional.get()

        return HttpResponse.ok(DetailsAuthorResponse(authorFromDb))
    }

    @Put("/{id}")
    @Transactional
    fun updateAuthor(@PathVariable id: Long, description: String) : HttpResponse<Any>{
        println("entrou put")
        val authorOptional = authorRepository.findById(id)

        authorOptional.let { if(it.isEmpty) return HttpResponse.notFound()  }

        val authorFromDb = authorOptional.get().let {
            it.description = description
            // .update() is not necessary because this object is in manager state by JPA
            // and we have @Transactional into this method.
            // For that, when method ends, this object will be updated automatically.
            // If Transactional is not used, .update() is necessary.
            authorRepository.update(it)
        }
        return HttpResponse.ok(DetailsAuthorResponse(authorFromDb))
    }

    @Delete("/{id}")
    @Transactional
    fun deleteAuthor(@PathVariable id: Long) : HttpResponse<Any>{
        println("entrou delete")
        val authorOptional = authorRepository.findById(id)

        authorOptional.let { if(it.isEmpty) return HttpResponse.notFound()  }

        val authorFromDb = authorOptional.get().let {
            authorRepository.delete(it)
        }
        return HttpResponse.ok()
    }
}