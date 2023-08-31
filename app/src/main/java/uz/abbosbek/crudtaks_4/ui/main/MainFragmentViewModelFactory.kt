package uz.abbosbek.crudtaks_4.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crudtaks_4.repository.Repository

@Suppress("UNCHECKED_CAST")
class MainFragmentViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(repository) as T
    }

}