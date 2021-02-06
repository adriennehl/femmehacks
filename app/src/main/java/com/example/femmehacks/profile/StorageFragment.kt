@file:Suppress("UNUSED_VARIABLE", "UNUSED_ANONYMOUS_PARAMETER")

package com.example.femmehacks.profile

import android.util.Log
import androidx.annotation.WorkerThread
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

class StorageFragment(val db: FirebaseFirestore) {

    fun setup() {
        val db = Firebase.firestore
    }

    fun addUser(name: String, email: String, subjects: Array<String>, role: String) {
        val users = db.collection("users");
        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "subjects" to subjects,
            "role" to role
        )
        users.add(user);
    }

    fun getUsersOfSubject(subject: String): List<String> {
        // Get users with matching subject.
        Log.d("TAG", subject)
        val users = db.collection("users");
        val query = users.whereArrayContains("subjects", subject);
        val snapshot = query.get().addOnSuccessListener { documents ->
            // Add users' names into a list and return.
            val userNames = mutableListOf<String>();
            for (document in documents) {
                userNames.add(document.get("name") as String);
            }
            Log.d("Tag", userNames.toString())
        }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            };
        return emptyList()
    }

    fun getUsersOfRole(role: String): Query {
        val users = db.collection("users");
        return users.whereEqualTo("role", role);
    }
}
