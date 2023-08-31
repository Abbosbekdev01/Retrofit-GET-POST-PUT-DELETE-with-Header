package uz.abbosbek.crudtaks_4.models.responses

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)