package com.example.femmehacks.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.femmehacks.R
import com.example.femmehacks.databinding.FragmentProfileBinding
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment: Fragment(), AdapterView.OnItemSelectedListener{
    lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentProfileBinding>(
            inflater,
            R.layout.fragment_profile, container, false
        )

        //SPINNER STUFF
        val spinner: Spinner = binding.spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.relationship_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }

        binding.saveButton.setOnClickListener{ save() };

        return binding.root
    }

    private fun save() {
        //add name
        val name = binding.name.text.toString()
        Log.d("Tag", name)
        val email = binding.email.text.toString()
        val role = binding.spinner.selectedItem.toString()
        val cs = binding.cs.isChecked
        val spanish = binding.spanish.isChecked
        val history = binding.history.isChecked
        val math = binding.math.isChecked

        val subjects = mutableListOf<String>()
        if (cs)
            subjects.add("CS")
        if (spanish)
            subjects.add("Spanish")
        if (history)
            subjects.add("History")
        if (math)
            subjects.add("Math")

        val storage = StorageFragment(FirebaseFirestore.getInstance())
        storage.addUser(name, email, subjects, role);



    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }



}