package uz.abbosbek.crudtaks_4.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.abbosbek.crudtaks_4.models.responses.Post
import uz.abbosbek.crudtaks_4.models.requests.PostRequest
import uz.abbosbek.crudtaks_4.models.responses.PostResponse
import uz.abbosbek.crudtaks_4.models.requests.PutRequest
import uz.abbosbek.crudtaks_4.repository.Repository

class MainFragmentViewModel(private val repository: Repository) : ViewModel() {
    var myResponse: MutableLiveData<List<Post>> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    var postResponse: MutableLiveData<PostResponse> = MutableLiveData()
    var editResponse: MutableLiveData<PostResponse> = MutableLiveData()
    var deleteResponse: MutableLiveData<String> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()

            if (response.isSuccessful) {
                myResponse.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun addPost(postRequest: PostRequest) {
        viewModelScope.launch {
            val response = repository.addPost(postRequest)
            if (response.isSuccessful) {
                postResponse.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun editPost(id: Int, putRequest: PutRequest) {
        viewModelScope.launch {
            val response = repository.editPost(id, putRequest)
            if (response.isSuccessful) {
                editResponse.value = response.body()
            } else {
                errorMessage.value = response.message()
            }
        }
    }

    fun deletePost(id: Int) {
        viewModelScope.launch {
            val response = repository.deletePost(id)
            if (response.isSuccessful) {
                deleteResponse.value = "Data deleted"
            } else {
                errorMessage.value = response.message()
            }
        }
    }
}