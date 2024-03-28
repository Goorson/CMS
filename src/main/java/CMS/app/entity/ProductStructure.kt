package CMS.app.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class ProductStructure (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    var name: String,
    var description: String,

    var categoryName: String?,
    var price: Double,
    var picture: String?
)