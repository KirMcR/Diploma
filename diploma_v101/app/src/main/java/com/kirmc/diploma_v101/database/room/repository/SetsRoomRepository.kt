package com.kirmc.diploma_v101.database.room.repository

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.database.ExercisesDatabaseRepository
import com.kirmc.diploma_v101.database.SetsDatabaseRepository
import com.kirmc.diploma_v101.database.room.dao.ExercisesRoomDao
import com.kirmc.diploma_v101.database.room.dao.SetsRoomDao
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.Sets

class SetsRoomRepository (private val setsRoomDao: SetsRoomDao):
    SetsDatabaseRepository {
    override val readAll: LiveData<List<Sets>>
        get() = setsRoomDao.getAllSets()

    override fun readSetsAll(exerciseId: Int): LiveData<List<Sets>> {
        return setsRoomDao.getSets(exerciseId)
    }
    override fun readSetsByDayAll(trainDayId: Int): LiveData<List<Sets>> {
        return setsRoomDao.getSetsByDay(trainDayId)
    }
    override suspend fun create(sets: Sets, onSuccess: () -> Unit) {
        setsRoomDao.addSets(sets = sets)
        onSuccess
    }

    override suspend fun update(sets: Sets, onSuccess: () -> Unit) {
        setsRoomDao.updateSets(sets = sets)
        onSuccess()
    }

    override suspend fun delete(sets: Sets, onSuccess: () -> Unit) {
        setsRoomDao.deleteSets(sets = sets)
        onSuccess()
    }

    override fun getSetQuantity(setId: Int): LiveData<String> {
        return setsRoomDao.getSetQuantity(setId)
    }

    override fun getSetWeight(setId: Int): LiveData<String> {
        return setsRoomDao.getSetWeight(setId)
    }

    override fun getExerciseId(setId: Int): LiveData<Int> {
        return setsRoomDao.getExerciseId(setId)
    }
}