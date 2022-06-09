package com.kirmc.diploma_v101.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kirmc.diploma_v101.model.Group
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.model.Sets
import com.kirmc.diploma_v101.model.TrainDays

@Dao
interface GroupRoomDao {

    @Query("SELECT * FROM group_table")
    fun getAllGroup(): LiveData<List<Group>>

    @Query("SELECT * FROM group_table WHERE companyId = :ditectorId")
    fun getGroup(ditectorId: Int): LiveData<List<Group>>

    @Query("SELECT companyId FROM group_table WHERE id =:groupId")
    fun getCompanyId(groupId:Int): LiveData<Int>

    @Insert
    suspend fun addGroup(group: Group)

    @Update
    suspend fun updateGroup(group: Group)

    @Delete
    suspend fun deleteGroup(group: Group)
}