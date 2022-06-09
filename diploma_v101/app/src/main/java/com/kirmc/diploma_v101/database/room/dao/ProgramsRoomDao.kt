package com.kirmc.diploma_v101.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.model.TrainDays

@Dao
interface ProgramsRoomDao {
    @Query("SELECT * FROM programs_table")
    fun getAllPrograms(): LiveData<List<Programs>>

    @Query("SELECT * FROM programs_table WHERE userId = :userId")
    fun getPrograms(userId: Int): LiveData<List<Programs>>

    @Query("SELECT * FROM programs_table WHERE isPublic = 1")
    fun getOnlinePrograms(): LiveData<List<Programs>>

    @Query("SELECT * FROM programs_table WHERE isAvailable = 1")
    fun getAvailablePrograms(): LiveData<List<Programs>>

    @Query("SELECT * FROM programs_table WHERE groupId = :groupId")
    fun getProgramsCompany(groupId: Int): LiveData<List<Programs>>

    @Query("SELECT userId FROM programs_table WHERE id =:programId")
    fun getUserId(programId:Int): LiveData<Int>

    @Query("SELECT groupId FROM programs_table WHERE id =:programId")
    fun getGroupId(programId:Int): LiveData<Int>

    @Query("SELECT name FROM programs_table WHERE id =:programId")
    fun getProgramName(programId:Int): LiveData<String>

    @Insert
    suspend fun addProgram(programs: Programs)

    @Update
    suspend fun updateProgram(programs: Programs)

    @Delete
    suspend fun deleteProgram(programs: Programs)
}