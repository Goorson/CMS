package CMS.app.entity

import jakarta.persistence.*
import jdk.jfr.Category

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    var name: String,
    var description: String,

    var categoryId: Int,
    var price: Double,
    var picture: String
)