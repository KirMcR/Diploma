package com.kirmc.diploma_v101.database.firebase
/*
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kirmc.diploma_v101.database.ProgramsDatabaseRepository
import com.kirmc.diploma_v101.database.UsersDatabaseRepository
import com.kirmc.diploma_v101.model.sPrograms
import com.kirmc.diploma_v101.model.Users
import com.kirmc.diploma_v101.utils.Constants
import com.kirmc.diploma_v101.utils.Constants.Keys.PROGRAM_ID
import com.kirmc.diploma_v101.utils.FIREBASE_ID
import com.kirmc.diploma_v101.utils.LOGIN
import com.kirmc.diploma_v101.utils.PASSWORD

class ProgramsFirebaseRepository : ProgramsDatabaseRepository {
    private val mAuth:  FirebaseAuth = FirebaseAuth.getInstance()
    override val readAll: LiveData<List<sPrograms>> = AllProgramsLiveData()
    private val database = Firebase.database.reference.child("PROGRAMS")

    override suspend fun create(programs: sPrograms, onSuccess: () -> Unit) {
        val programId = database.push().key.toString()
        val mapPrograms = hashMapOf<String, Any>()

        mapPrograms[PROGRAM_ID] = programId
        mapPrograms[Constants.Keys.NAME] = programs.name
        mapPrograms[Constants.Keys.DESCRIPTION] = programs.description

        database.child((programId)).updateChildren(mapPrograms).addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to add new note") }
    }

    override suspend fun update(programs: sPrograms, onSuccess: () -> Unit) {
        val programId = programs.programId
        val mapPrograms = hashMapOf<String, Any>()

        mapPrograms[FIREBASE_ID] = programId
        mapPrograms[Constants.Keys.TITLE] = programs.name
        mapPrograms[Constants.Keys.SUBTITLE] = programs.description

        database.child(programId)
            .updateChildren(mapPrograms)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to update note") }
    }

    override suspend fun delete(programs: sPrograms, onSuccess: () -> Unit) {
        database.child(programs.programId).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed delete note") }

    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {


        mAuth.signInAnonymously()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
            }

        //  val programId = database.push().key.toString()
       // val mapPrograms = hashMapOf<String, Any>()

    //    mapPrograms[FIREBASE_ID] = programId
    //    mapPrograms[Constants.Keys.TITLE] = LOGIN
     //   mapPrograms[Constants.Keys.SUBTITLE] = PASSWORD
    //    database.child((programId)).updateChildren(mapPrograms).addOnSuccessListener { onSuccess() }
       //     .addOnFailureListener { Log.d("checkData", "Failed to add new note") }


    }
}

// }

*/