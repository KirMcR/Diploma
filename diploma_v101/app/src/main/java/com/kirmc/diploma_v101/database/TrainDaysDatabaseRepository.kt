package com.kirmc.diploma_v101.database

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.model.TrainDays

interface TrainDaysDatabaseRepository  {
    val readAll: LiveData<List<TrainDays>>
    fun readProgramAll(programId: Int): LiveData<List<TrainDays>>
    fun getProgramId(trainDayId: Int): LiveData<Int>
    fun getTrainDayName(trainDayId: Int): LiveData<String>
    suspend fun create(trainDay: TrainDays, onSuccess: () -> Unit)
    suspend fun update(trainDay: TrainDays, onSuccess: () -> Unit)
    suspend fun delete(trainDay: TrainDays, onSuccess: () -> Unit)

}