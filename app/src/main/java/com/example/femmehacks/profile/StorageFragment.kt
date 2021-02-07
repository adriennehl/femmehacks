@file:Suppress("UNUSED_VARIABLE", "UNUSED_ANONYMOUS_PARAMETER")

package com.example.femmehacks.profile

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.femmehacks.match.MatchFragment
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.ArrayList
import java.util.Date
import java.util.HashMap
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

const val USERS = "users";
const val NAME = "name";
const val EMAIL = "email";
const val ROLE = "role";
const val SUBJECTS = "subjects";
var matchingUsers = mutableListOf<User>();

class User constructor(name_input: String, email_input: String, role_input: String,
                       subject_input: List<String>) {
    var name = name_input;
    var email = email_input;
    var role = role_input;
    var subjects = subject_input;

    override fun toString(): String {
        val string = "Name: $name\nEmail: $email\nRole: $role\nSubjects: $subjects\n";
        return string;
    }
}

class StorageFragment(val db: FirebaseFirestore) {

    fun setup() {
        val db = Firebase.firestore
    }


    fun addUser(name: String, email: String, subjects: List<String>, role: String) {
        val users = db.collection(USERS);
        val user = hashMapOf(
            NAME to name,
            EMAIL to email,
            SUBJECTS to subjects,
            ROLE to role
        )
        users.add(user);
    }

    /*
    Adds the users with the matching role and any matching subjects to the list matchingUsers.
     */
    fun getMatchingUsers(role: String, subjects: List<String>, fragment: MatchFragment) {
        val users = db.collection(USERS);
        //  Query the users with the matching role and any of the matching subjects.
        val query = users.whereEqualTo(ROLE, role).whereArrayContainsAny(SUBJECTS, subjects);
        query.get().addOnSuccessListener { documents ->
            matchingUsers.clear()
            // Add each matching user to the list of matching users as a User object.
            for (document in documents) {
                val user = User(document.get(NAME) as String, document.get(EMAIL) as String,
                    document.get(ROLE) as String, document.get(SUBJECTS) as List<String>);
                matchingUsers.add(user);
            }
            fragment.setUsersList(matchingUsers)
        }.addOnFailureListener { exception ->
            Log.d("TAG", "Error getting documents: ", exception)
        };
    }
}
