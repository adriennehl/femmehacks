package com.example.femmehacks.match

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.femmehacks.MainActivity
import com.example.femmehacks.R
import com.example.femmehacks.databinding.FragmentMatchBinding
import com.example.femmehacks.profile.StorageFragment
import com.example.femmehacks.profile.User
import com.example.femmehacks.profile.matchingUsers
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import io.grpc.Context

import com.firebase.ui.auth.AuthUI
//import com.google.firebase.quickstart.auth.R


class MatchFragment: Fragment() {
    lateinit var binding : FragmentMatchBinding
    var userList = mutableListOf<User>()

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

        binding.filterButton.setOnClickListener { filter() }
        setHasOptionsMenu(true)

        /* Update recycler view contents */
        val adapter = UserItemAdapter(userList)
        binding.matchList.adapter = adapter
//        Log.d("Log", binding.matchList.adapter!!.itemCount.toString())
//        (binding.matchList.adapter as UserItemAdapter).notifyDataSetChanged()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_action, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun setUsersList(user: MutableList<User>){
        val adapter = UserItemAdapter(user)
        binding.matchList.adapter = adapter
    }

    fun filter() {
        val subjects = mutableListOf<String>()
        for (child in binding.subjectsGroup.children) {
            if (child is CheckBox && child.isChecked) {
                subjects.add(child.text.toString())
            }
        }
        var role = "Mentor"
        val checkedPos = binding.rolesGroup.checkedRadioButtonId
        val checkedButton = activity?.findViewById<RadioButton>(checkedPos)
        if (checkedButton != null) {
            role = checkedButton.text.toString()
        }
        val storage = StorageFragment(FirebaseFirestore.getInstance())
        storage.getMatchingUsers(role, subjects, this);
    }

    /**
     * Filter action bar button is pressed
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId.equals(R.id.filter_bar_button)) {
            filterPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * hide/show filter layout when action bar button is pressed
     */
    fun filterPressed() {
        if (binding.filterLayout.visibility.equals(View.GONE)) {
            binding.filterLayout.visibility = View.VISIBLE
        } else {
            binding.filterLayout.visibility = View.GONE
        }
    }
}