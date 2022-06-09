package com.kirmc.diploma_v101.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kirmc.diploma_v101.model.Users

@Dao
interface UsersRoomDao{
    @Query("SELECT * FROM users_table")
    fun getAllUsers(): LiveData<List<Users>>

    @Query("SELECT * FROM users_table WHERE groupId =:directorId")
    fun getUsersDirector(directorId:Int): LiveData<List<Users>>

    @Query("SELECT groupId FROM users_table WHERE id =:userId")
    fun getUserGroupId(userId:Int): LiveData<Int>

    @Query("SELECT companyId FROM users_table WHERE id =:userId")
    fun getUserCompanyId(userId:Int): LiveData<Int>

    @Query("SELECT isCompany FROM users_table WHERE id =:userId")
    fun getIsUserCompany(userId:Int): LiveData<Int>
    // @Insert
    //suspend fun insertTrainDays(trainDays: TrainDays)


    @Query("SELECT * FROM users_table WHERE isModer = 1")
    fun getModers(): LiveData<List<Users>>

    @Query("SELECT * FROM users_table WHERE isAdmin = 1")
    fun getAdmins(): LiveData<List<Users>>

    @Query("SELECT * FROM users_table WHERE isCompany = 1")
    fun getCompanies(): LiveData<List<Users>>

    @Query("SELECT * FROM users_table WHERE isModer = 0 AND isCompany = 0 AND isAdmin = 0 AND isModer = 0")
    fun getUsualUsers(): LiveData<List<Users>>


    @Insert
    suspend fun addUsers(users: Users)

    @Update
    suspend fun updateUsers(users: Users)

    @Delete
    suspend fun deleteUsers(users: Users)
}