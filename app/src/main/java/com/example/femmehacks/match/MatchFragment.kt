package com.example.femmehacks.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.femmehacks.R
import com.example.femmehacks.databinding.FragmentMatchBinding

import com.firebase.ui.auth.AuthUI
//import com.google.firebase.quickstart.auth.R


class MatchFragment: Fragment() {
    lateinit var binding : FragmentMatchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentMatchBinding>(
            inflater,
            R.layout.fragment_match, container, false
        )
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build())

                    // Create and launch sign-in intent
                    startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
            RC_SIGN_IN)
        return binding.root
    }
    companion object {

        private const val RC_SIGN_IN = 123
    }
}