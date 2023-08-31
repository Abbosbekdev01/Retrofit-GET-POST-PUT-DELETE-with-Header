package uz.abbosbek.crudtaks_4.models.requests

data class PostRequest(
    val userId: Int = 1,
    val body: String,
    val title: String
)