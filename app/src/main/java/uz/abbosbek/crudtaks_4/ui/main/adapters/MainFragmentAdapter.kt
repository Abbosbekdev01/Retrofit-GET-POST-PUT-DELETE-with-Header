package uz.abbosbek.crudtaks_4.ui.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.crudtaks_4.databinding.ItemRvBinding
import uz.abbosbek.crudtaks_4.models.responses.Post

class MainFragmentAdapter(var list: ArrayList<Post> = ArrayList(), val rvClick: RvClick) :
    RecyclerView.Adapter<MainFragmentAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(post: Post, position: Int) {
            itemRvBinding.titleTxt.text = post.title

            itemRvBinding.moreMenu.setOnClickListener {
                rvClick.menuClick(itemRvBinding.moreMenu, post)
            }
            itemRvBinding.root.setOnClickListener {
                rvClick.itemClick(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Post>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    interface RvClick {
        fun menuClick(imageView: ImageView, post: Post)
        fun itemClick(post: Post)
    }
}