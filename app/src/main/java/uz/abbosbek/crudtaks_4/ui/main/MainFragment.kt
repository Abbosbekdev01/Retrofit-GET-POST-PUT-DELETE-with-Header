package uz.abbosbek.crudtaks_4.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.abbosbek.crudtaks_4.R
import uz.abbosbek.crudtaks_4.databinding.BottomShitDialogBinding
import uz.abbosbek.crudtaks_4.databinding.FragmentMainBinding
import uz.abbosbek.crudtaks_4.databinding.ItemEditDialogBinding
import uz.abbosbek.crudtaks_4.models.requests.PostRequest
import uz.abbosbek.crudtaks_4.models.requests.PutRequest
import uz.abbosbek.crudtaks_4.models.responses.Post
import uz.abbosbek.crudtaks_4.repository.Repository
import uz.abbosbek.crudtaks_4.ui.main.adapters.MainFragmentAdapter

private const val TAG = "Main"

class MainFragment : Fragment(), MainFragmentAdapter.RvClick {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainFragmentViewModel
    private val myAdapter by lazy { MainFragmentAdapter(rvClick = this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setupRecyclerView()


        val repository = Repository()
        val viewModelFactory = MainFragmentViewModelFactory(repository)
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[MainFragmentViewModel::class.java]

        viewModel.getPost()
        viewModel.myResponse.observe(viewLifecycleOwner) { response ->
            myAdapter.setData(response)
            Log.d(TAG, "onCreateView: $response")
        }

        //todo: viewModel dan -> ma'lumotlarni o'zgarganini bilish uchun yozilgan kod
        viewModel.postResponse.observe(viewLifecycleOwner) { response ->
            Toast.makeText(
                requireContext(),
                "title: ${response.title}\nbody: ${response.body}",
                Toast.LENGTH_LONG
            ).show()
        }

        //todo: ma'lumotni o'zgartirilganini bilish uchun
        viewModel.editResponse.observe(viewLifecycleOwner) { response ->
            Toast.makeText(
                requireContext(),
                "title: ${response.title}\nbody: ${response.body}",
                Toast.LENGTH_LONG
            ).show()
        }

        //todo: ma'lumot o'chirilganini bilish uchun
        viewModel.deleteResponse.observe(viewLifecycleOwner) { response ->
            Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show()
        }

        //todo: ma'lumot xatoligini bilish uchun
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()

        }

        binding.btnAdd.setOnClickListener {
            openDialog()
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun openDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val bottomDialog = BottomShitDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bottomDialog.root)
        dialog.create()

        bottomDialog.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        bottomDialog.btnSave.setOnClickListener {
            val title = bottomDialog.editTitle.text.toString().trim()
            val body = bottomDialog.editBody.text.toString().trim()
            val postRequest = PostRequest(body = body, title = title)

            if (title.isNotEmpty() && body.isNotEmpty()) {
                viewModel.addPost(postRequest)
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Data is Empty", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    //todo: keyingi oynaga id orqali ma'lumot olib o'tilgan
    override fun itemClick(post: Post) {
        findNavController().navigate(
            R.id.action_mainFragment_to_secontFragment,
            bundleOf("id" to post.id)
        )
    }

    override fun menuClick(imageView: ImageView, post: Post) {
        val popupMenu = PopupMenu(requireContext(), imageView)
        popupMenu.inflate(R.menu.post_manu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_edit -> {
                    editPost(post)
                }

                R.id.item_delete -> {
                    deletePost(post)
                }
            }
            true
        }
        popupMenu.show()
    }

    private fun editPost(post: Post) {
        val editDialog = BottomSheetDialog(requireContext())
        val dialogEdit = ItemEditDialogBinding.inflate(layoutInflater)
        editDialog.setContentView(dialogEdit.root)
        editDialog.create()

        dialogEdit.apply {
            editTitle.setText(post.title)
            editBody.setText(post.body)

            btnClose.setOnClickListener {
                editDialog.dismiss()
            }


            btnSave.setOnClickListener {
                val title = editTitle.text.toString().trim()
                val body = editBody.text.toString().trim()

                if (title.isNotEmpty() && body.isNotEmpty()) {
                    val putRequest = PutRequest(
                        postId = post.id,
                        body = body,
                        title = title,
                    )
                    viewModel.editPost(post.id, putRequest)
                    editDialog.dismiss()
                } else {
                    Toast.makeText(requireContext(), "Data is Empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
        editDialog.show()

    }

    private fun deletePost(post: Post) {
        viewModel.deletePost(post.id)
    }

}