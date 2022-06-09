package com.kirmc.diploma_v101.database

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.TrainDays

interface ExercisesDatabaseRepository  {
    val readAll: LiveData<List<Exercises>>
    fun readTrainDayAll(trainDayId: Int): LiveData<List<Exercises>>
    fun getTrainDayId(exerciseId: Int): LiveData<Int>
    suspend fun create(exercises: Exercises, onSuccess: () -> Unit)
    suspend fun update(exercises: Exercises, onSuccess: () -> Unit)
    suspend fun delete(exercises: Exercises, onSuccess: () -> Unit)

    fun getExercisesName(exerciseId: Int): LiveData<String>

    fun getExercise(exerciseId: Int): LiveData<Exercises>

}