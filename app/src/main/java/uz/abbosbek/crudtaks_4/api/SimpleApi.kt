package uz.abbosbek.crudtaks_4.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.abbosbek.crudtaks_4.models.responses.Post
import uz.abbosbek.crudtaks_4.models.requests.PostRequest
import uz.abbosbek.crudtaks_4.models.responses.PostResponse
import uz.abbosbek.crudtaks_4.models.requests.PutRequest

interface SimpleApi {

    @GET("/posts")
    suspend fun getPost(): Response<List<Post>>

    @GET("/posts/{id}")
    suspend fun getPostById(
        @Path("id") id: Int
    ):Response<Post>

    @POST("/posts")
    suspend fun addPost(
        @Body postRequest: PostRequest
    ): Response<PostResponse>

    @PUT("/posts/{id}")
    suspend fun editPost(
        @Path("id") id: Int,
        @Body putRequest: PutRequest
    ): Response<PostResponse>

    @DELETE("/posts/{id}")
    suspend fun deletePost(
        @Path("id") id: Int
    ): Response<Any>
}