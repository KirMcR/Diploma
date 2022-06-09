package com.kirmc.diploma_v101.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kirmc.diploma_v101.model.*


@Dao
interface TrainingRoomDao{
    @Query("SELECT * FROM training_table")
    fun getAllTraining(): LiveData<List<Training>>

    @Query("SELECT * FROM training_table WHERE setId = :isPlay")
    fun getTraining(isPlay: Int): LiveData<List<Training>>

    @Query("SELECT dateStart FROM training_table WHERE id = :trainingId")
    fun getTrainingStart(trainingId: Int): LiveData<String>

    @Query("SELECT * FROM training_table WHERE userid =:userId")
    fun getTrainingByUserId(userId:Int): LiveData<List<Training>>

    @Query("SELECT * FROM training_table WHERE userid =:userId AND exerciseId =:exerciseId AND dateStart =:dateStart")
    fun getTrainingByEverything(userId:Int, exerciseId:Int, dateStart:String): LiveData<List<Training>>

    @Insert
    suspend fun addTraining(training: Training)

    @Update
    suspend fun updateTraining(training: Training)

    @Delete
    suspend fun deleteTraining(training: Training)
}