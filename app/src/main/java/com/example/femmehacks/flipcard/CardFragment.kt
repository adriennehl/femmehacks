package com.example.femmehacks

import android.os.Bundle
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.databinding.DataBindingUtil

class CardFragment : Fragment() {

    lateinit var front_anim:AnimatorSet
    lateinit var back_anim:AnimatorSet
    var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card)
        val scale:Float = applicationContext.resources.displayMetrics.density
        card_front.cameraDistance = 8000 * scale
        card_back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        flip_btn.setOnClickListener {
            if(isFront){
                front_anim.setTarget(card_front)
                back_anim.setTarget(card_back)
                front_anim.start()
                back_anim.start()
                isFront = false
            }else{
                front_anim.setTarget(card_back)
                back_anim.setTarget(card_front)
                back_anim.start()
                front_anim.start()
                isFront = true
            }

        }
    }
}