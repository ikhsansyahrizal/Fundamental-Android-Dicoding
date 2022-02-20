package com.dicoding.fundamentalandroid_submission2_ikhsan.mainActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.fundamentalandroid_submission2_ikhsan.databinding.ItemRowUsersBinding
import com.dicoding.fundamentalandroid_submission2_ikhsan.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private val list = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =  ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ListViewHolder(private val binding: ItemRowUsersBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(user: User){

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {

                tvItemUsername.text = user.login
                tvItemUrl.text = user.url
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(imgItemPhoto)

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<User>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(user: User)
    }

}