package com.dicoding.fundamentalandroid_submission1_ikhsan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.fundamentalandroid_submission1_ikhsan.databinding.ItemRowUsersBinding

class ListUserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, name, location, photo) = listUser[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemUsername.text = username
        holder.binding.tvItemName.text = name
        holder.binding.tvItemLocation.text = location


        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }

    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder(var binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}