package uz.abbosbek.crudtaks_4.ui.main_item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.abbosbek.crudtaks_4.models.responses.Post
import uz.abbosbek.crudtaks_4.repository.Repository

class SecondFragmentViewModel(private val repository: Repository) : ViewModel() {

    val errorManager: MutableLiveData<String> = MutableLiveData()
    val post: MutableLiveData<Post> = MutableLiveData()

    fun getPostById(id: Int) {
        viewModelScope.launch {
            val response = repository.getPostById(id)
            if (response.isSuccessful) {
                post.value = response.body()
            } else {
                errorManager.value = response.message()
            }
        }
    }

}