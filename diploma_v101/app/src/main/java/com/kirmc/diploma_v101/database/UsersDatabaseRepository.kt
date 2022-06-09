package com.kirmc.diploma_v101.database

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.model.Training
import com.kirmc.diploma_v101.model.Users

interface UsersDatabaseRepository {
    val readAll: LiveData<List<Users>>
    fun readUsersAll(directorId: Int): LiveData<List<Users>>

    fun getUserGroupId(userId: Int): LiveData<Int>

    fun getUserCompanyId(userId: Int): LiveData<Int>

    fun getIsCompanyId(userId: Int): LiveData<Int>

    fun getModers(): LiveData<List<Users>>
    fun getAdmins(): LiveData<List<Users>>
    fun getCompanies(): LiveData<List<Users>>
    fun getUsualUsers(): LiveData<List<Users>>

    suspend fun create(users: Users, onSuccess: () -> Unit)
    suspend fun update(users: Users, onSuccess: () -> Unit)
    suspend fun delete(users: Users, onSuccess: () -> Unit)

  //  fun signOut(){}
    //fun connectToDatabase(onSuccess: () -> Unit, onFail:(String)->Unit){}
}