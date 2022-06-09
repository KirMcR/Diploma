/*package com.kirmc.diploma_v101.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kirmc.diploma_v101.model.sPrograms
import com.kirmc.diploma_v101.model.Users

class AllProgramsLiveData: LiveData<List<sPrograms>>() {
    //  private val mAuth = FirebaseAuth.getInstance()
    //private val database = Firebase.database.reference
    //  .child(mAuth.currentUser?.uid.toString())
    private val database = Firebase.database.reference.child("PROGRAMS")

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val programs = mutableListOf<sPrograms>()
            snapshot.children.map{
                programs.add(it.getValue(sPrograms::class.java) ?: sPrograms())
            }
            value = programs
        }

        override fun onCancelled(error: DatabaseError) {}
    }

    override fun onActive() {
        database.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        database.removeEventListener(listener)
        super.onInactive()
    }
}*/