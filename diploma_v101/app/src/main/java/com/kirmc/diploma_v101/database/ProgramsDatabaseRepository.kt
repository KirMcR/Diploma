package com.kirmc.diploma_v101.database

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.Programs

interface ProgramsDatabaseRepository {
    val readAll: LiveData<List<Programs>>
    fun readProgramsAll(userId: Int): LiveData<List<Programs>>
    fun readProgramsCompany(groupId: Int): LiveData<List<Programs>>
    fun readOnlineProgramsAll(): LiveData<List<Programs>>

    fun getAvailablePrograms(): LiveData<List<Programs>>
    fun getUserId(programId: Int): LiveData<Int>
    fun getProgramName(programId: Int): LiveData<String>
    fun getGroupId(programId: Int): LiveData<Int>

    suspend fun create(programs: Programs, onSuccess: () -> Unit)
    suspend fun update(programs: Programs, onSuccess: () -> Unit)
    suspend fun delete(programs: Programs, onSuccess: () -> Unit)

  //  fun signOut(){}
    //fun connectToDatabase(onSuccess: () -> Unit, onFail:(String)->Unit){}
}