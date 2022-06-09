package com.kirmc.diploma_v101.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kirmc.diploma_v101.model.Sets


@Dao
interface SetsRoomDao {
    @Query("SELECT * FROM sets_table")
    fun getAllSets(): LiveData<List<Sets>>

    @Query("SELECT * FROM sets_table WHERE exerciseId = :exerciseId")
    fun getSets(exerciseId: Int): LiveData<List<Sets>>

    @Query("SELECT * FROM sets_table WHERE trainDayId = :trainDayId")
    fun getSetsByDay(trainDayId: Int): LiveData<List<Sets>>

    @Query("SELECT exerciseId FROM sets_table WHERE id =:setId")
    fun getExerciseId(setId:Int): LiveData<Int>

    @Query("SELECT quantity FROM sets_table WHERE id =:setId")
    fun getSetQuantity(setId:Int): LiveData<String>

    @Query("SELECT weight FROM sets_table WHERE id =:setId")
    fun getSetWeight(setId:Int): LiveData<String>

    @Insert
    suspend fun addSets(sets: Sets)

    @Update
    suspend fun updateSets(sets: Sets)

    @Delete
    suspend fun deleteSets(sets: Sets)
}