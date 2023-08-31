package uz.abbosbek.crudtaks_4.models.responses

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)