package uz.abbosbek.crudtaks_4.repository

import retrofit2.Response
import uz.abbosbek.crudtaks_4.api.RetrofitInstance
import uz.abbosbek.crudtaks_4.models.responses.Post
import uz.abbosbek.crudtaks_4.models.requests.PostRequest
import uz.abbosbek.crudtaks_4.models.responses.PostResponse
import uz.abbosbek.crudtaks_4.models.requests.PutRequest

class Repository {

    suspend fun getPost(): Response<List<Post>> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPostById(id: Int): Response<Post> {
        return RetrofitInstance.api.getPostById(id)
    }

    suspend fun addPost(postRequest: PostRequest): Response<PostResponse> {
        return RetrofitInstance.api.addPost(postRequest)
    }

    suspend fun editPost(id: Int, putRequest: PutRequest): Response<PostResponse> {
        return RetrofitInstance.api.editPost(id, putRequest)
    }

    suspend fun deletePost(id: Int): Response<Any> {
        return RetrofitInstance.api.deletePost(id)
    }
}