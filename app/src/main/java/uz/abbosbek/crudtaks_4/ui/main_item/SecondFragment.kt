package uz.abbosbek.crudtaks_4.ui.main_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.abbosbek.crudtaks_4.databinding.FragmentSecontBinding
import uz.abbosbek.crudtaks_4.models.responses.Post
import uz.abbosbek.crudtaks_4.repository.Repository


class SecondFragment : Fragment() {

    private val binding by lazy { FragmentSecontBinding.inflate(layoutInflater) }
    private lateinit var viewModel: SecondFragmentViewModel
    private var postId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postId = arguments?.getInt("id") ?: -1

        val repository = Repository()
        val viewModelFactory = SecondFragmentViewModelFactory(repository)
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[SecondFragmentViewModel::class.java]

        viewModel.getPostById(postId!!)

        viewModel.post.observe(viewLifecycleOwner) { response ->
            binding.postBody.text = response.body
        }

        viewModel.errorManager.observe(viewLifecycleOwner) { response ->
            Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show()
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}