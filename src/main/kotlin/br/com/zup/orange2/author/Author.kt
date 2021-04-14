package br.com.zup.orange2.author

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Author(
    val name: String,
    val email: String,
    var description: String,
    val address: Address
){
    @Id
    @GeneratedValue
    var id: Long? = null

    val createdAt: LocalDateTime = LocalDateTime.now()


    override fun toString(): String {
        return "Name: $name | Email: $email | Address: ${address.street} - ${address.number} - ${address.city} - ${address.state}"
    }
}