package br.com.zup.orange2

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Author(
    val name: String,
    val email: String,
    var description: String
){
    @Id
    @GeneratedValue
    var id: Long? = null

    val createdAt: LocalDateTime = LocalDateTime.now()
}