/*package com.kirmc.diploma_v101.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kirmc.diploma_v101.database.UsersDatabaseRepository
import com.kirmc.diploma_v101.model.Users
import com.kirmc.diploma_v101.utils.Constants
import com.kirmc.diploma_v101.utils.FIREBASE_ID
import com.kirmc.diploma_v101.utils.LOGIN
import com.kirmc.diploma_v101.utils.PASSWORD

class UsersFirebaseRepository : UsersDatabaseRepository {
 //   private val mAuth = FirebaseAuth.getInstance()
    private val mAuth:  FirebaseAuth = FirebaseAuth.getInstance()

    override val readAll: LiveData<List<Users>> = AllUsersLiveData()
    private val database = Firebase.database.reference.child("USERS")
   // private val database = databaseFitst
   //   .child(mAuth.currentUser?.uid.toString())
    // private val database = Firebase.database.reference
     //   .child(mAuth.currentUser?.uid.toString())
   // private val database1 = Firebase.database.reference.child("")

 override suspend fun create(users: Users, onSuccess: () -> Unit) {
     val usersId = database.push().key.toString()
     val mapUsers = hashMapOf<String, Any>()

     mapUsers[FIREBASE_ID] = usersId
     mapUsers[Constants.Keys.TITLE] = users.login
     mapUsers[Constants.Keys.SUBTITLE] = users.password

     database.child((usersId)).updateChildren(mapUsers).addOnSuccessListener { onSuccess() }
         .addOnFailureListener { Log.d("checkData", "Failed to add new note") }
 }

 /*   override suspend fun update(note: Users, onSuccess: () -> Unit) {
        val noteId = note.firebaseId
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to update note") }
    }

    override suspend fun delete(note: Users, onSuccess: () -> Unit)
    {
        database.child(note.firebaseId).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed delete note") }

    }
*/
    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
       /* mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString())}*/

        mAuth.signInAnonymously()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                 }

               val usersId = database.push().key.toString()
                val mapUsers = hashMapOf<String, Any>()

                mapUsers[FIREBASE_ID] = usersId
                mapUsers[Constants.Keys.TITLE] = LOGIN
                mapUsers[Constants.Keys.SUBTITLE] = PASSWORD
                database.child((usersId)).updateChildren(mapUsers).addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { Log.d("checkData", "Failed to add new note") }


             /* val usersId1 = database1.push().key.toString()
                val mapUsers1 = hashMapOf<String, Any>()

                mapUsers1[FIREBASE_ID] = usersId1
                mapUsers1[Constants.Keys.TITLE] = LOGIN
                mapUsers1[Constants.Keys.SUBTITLE] = PASSWORD
                database1.child((usersId1)).updateChildren(mapUsers1).addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { Log.d("checkData", "Failed to add new note") }
*/

            }

   // }

}*/