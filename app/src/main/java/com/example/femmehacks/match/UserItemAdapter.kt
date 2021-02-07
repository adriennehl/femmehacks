package com.example.femmehacks.match

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.femmehacks.databinding.UserItemBinding
import com.example.femmehacks.profile.User

class UserItemAdapter (private val data: List<User>) :
RecyclerView.Adapter<UserItemAdapter.UserViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserItemAdapter.UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class UserViewHolder private constructor(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.nameText.text = item.name
        }

        companion object {
            lateinit var binding: UserItemBinding

            fun from(parent: ViewGroup): UserViewHolder {
                binding = UserItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return UserViewHolder(binding)
            }
        }
    }
}