package uz.abbosbek.crudtaks_4.models.requests

data class PutRequest(
    val postId: Int,
    val body: String,
    val title: String,
    val userId: Int = 1
)