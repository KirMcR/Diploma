package com.kirmc.diploma_v101.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.TrainDays


@Dao
interface ExercisesRoomDao {
    @Query("SELECT * FROM exercises_table")
    fun getAllExercises(): LiveData<List<Exercises>>

    @Query("SELECT * FROM exercises_table WHERE trainDayId = :trainDayId")
    fun getTrainDaysExercise(trainDayId: Int): LiveData<List<Exercises>>

    @Query("SELECT trainDayId FROM exercises_table WHERE id =:exerciseId")
    fun getTrainDayId(exerciseId:Int): LiveData<Int>

    @Query("SELECT id, name, description,trainDayId FROM exercises_table WHERE id =:exerciseId")
    fun getExercise(exerciseId:Int): LiveData<Exercises>

    @Query("SELECT name FROM exercises_table WHERE id =:exerciseId")
    fun getExercisesName(exerciseId:Int): LiveData<String>

    @Insert
    suspend fun addExercises(exercises: Exercises)

    @Update
    suspend fun updateExercises(exercises: Exercises)

    @Delete
    suspend fun deleteExercises(exercises: Exercises)
}