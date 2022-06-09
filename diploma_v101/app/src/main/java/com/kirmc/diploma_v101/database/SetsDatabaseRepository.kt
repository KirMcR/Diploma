package com.kirmc.diploma_v101.database

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.model.Sets


interface SetsDatabaseRepository  {
    val readAll: LiveData<List<Sets>>
    fun readSetsAll(exerciseId: Int): LiveData<List<Sets>>
    fun readSetsByDayAll(trainDayId: Int): LiveData<List<Sets>>

    fun getExerciseId(setId: Int): LiveData<Int>

    fun getSetQuantity(setId: Int): LiveData<String>
    fun getSetWeight(setId: Int): LiveData<String>

    suspend fun create(sets: Sets, onSuccess: () -> Unit)
    suspend fun update(sets: Sets, onSuccess: () -> Unit)
    suspend fun delete(sets: Sets, onSuccess: () -> Unit)
}