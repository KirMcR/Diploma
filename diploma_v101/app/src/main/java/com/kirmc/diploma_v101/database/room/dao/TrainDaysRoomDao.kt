package com.kirmc.diploma_v101.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kirmc.diploma_v101.model.Programs

import com.kirmc.diploma_v101.model.TrainDays

@Dao
interface TrainDaysRoomDao {
    @Query("SELECT * FROM train_days_table WHERE programId = :programId")
    fun getProgramsTrainDay(programId: Int): LiveData<List<TrainDays>>

    @Query("SELECT * FROM train_days_table")
    fun getAllTrainDay(): LiveData<List<TrainDays>>

    @Query("SELECT programId FROM train_days_table WHERE id =:trainDayId")
    fun getProgramId(trainDayId:Int): LiveData<Int>

    @Query("SELECT name FROM train_days_table WHERE id =:trainDayId")
    fun getTrainDayName(trainDayId:Int): LiveData<String>

    @Insert
    suspend fun addTrainDay(trainDay: TrainDays)

    @Update
    suspend fun updateTrainDay(trainDay: TrainDays)

    @Delete
    suspend fun deleteTrainDay(trainDay: TrainDays)
}