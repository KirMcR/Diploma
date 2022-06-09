/*package com.kirmc.diploma_v101.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kirmc.diploma_v101.model.Users

class AllUsersLiveData: LiveData<List<Users>>() {
  //  private val mAuth = FirebaseAuth.getInstance()
    //private val database = Firebase.database.reference
      //  .child(mAuth.currentUser?.uid.toString())
  private val database = Firebase.database.reference.child("USERS")

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val users = mutableListOf<Users>()
            snapshot.children.map{
                users.add(it.getValue(Users::class.java) ?: Users())
            }
            value = users
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