package CMS.app.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Category")
data class Category (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    var name: String,
    var description: String
)