package uz.abbosbek.crudtaks_4.ui.main_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crudtaks_4.repository.Repository

class SecondFragmentViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SecondFragmentViewModel(repository) as T
    }
}