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

    private fun setup() {
        val db = Firebase.firestore
    }

    private fun addUsers() {
        val users = db.collection("users");

        val anna = hashMapOf(
            "name" to "Anna Bai",
            "email" to "annabai@gmail.com",
            "subjects" to arrayOf("Computer Science, Chinese"),
            "bio" to "I go to Rice University!"
        )
        users.add(anna);

        val janet = hashMapOf(
            "name" to "Janet Lu",
            "email" to "janetlu@gmail.com",
            "subjects" to arrayOf("Computer Science, Electrical Engineering"),
            "bio" to "I go to Rice University!"
        )
        users.add(janet);
    }

    private fun getCompSciUsers() {
        val users = db.collection("users");

        users.whereArrayContains("subjects, Computer Science");
    }
}