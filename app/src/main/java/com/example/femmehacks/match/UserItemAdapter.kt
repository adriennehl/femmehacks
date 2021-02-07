package com.example.femmehacks.match

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.femmehacks.R
import com.example.femmehacks.databinding.UserItemBinding
import com.example.femmehacks.databinding.CardBinding
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

    class UserViewHolder private constructor(val binding: CardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.nameText.text = item.name
            binding.emailText.text = item.email
            var subjectStr = ""
            for (subject in item.subjects) {
                subjectStr += subject + " "
            }
            binding.subjectText.text = subjectStr
            binding.roleText.text = item.role
        }

        companion object {
            lateinit var binding: CardBinding

            fun from(parent: ViewGroup): UserViewHolder {
                binding = CardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                var applicationContext = parent.context
                val scale:Float = applicationContext.resources.displayMetrics.density
                binding.cardFront.cameraDistance = 8000 * scale
                binding.cardBack.cameraDistance = 8000 * scale

                var front_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_anim) as AnimatorSet
                var back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_anim) as AnimatorSet

//                binding.flipBtn.setOnClickListener {
//                    if(binding.flipBtn.isFront){
//                        front_anim.setTarget(binding.cardFront)
//                        back_anim.setTarget(binding.cardBack)
//                        front_anim.start()
//                        back_anim.start()
//                        isFront = false
//                    }else{
//                        front_anim.setTarget(binding.cardBack)
//                        back_anim.setTarget(binding.cardFront)
//                        back_anim.start()
//                        front_anim.start()
//                        isFront = true
//                    }
//
//                }

                return UserViewHolder(binding)
            }
        }
    }
}