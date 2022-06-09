package com.kirmc.diploma_v101.database.room.repository

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.database.TrainingDatabaseRepository
import com.kirmc.diploma_v101.database.UsersDatabaseRepository
import com.kirmc.diploma_v101.database.room.dao.TrainingRoomDao
import com.kirmc.diploma_v101.database.room.dao.UsersRoomDao
import com.kirmc.diploma_v101.model.Sets
import com.kirmc.diploma_v101.model.Training
import com.kirmc.diploma_v101.model.Users

class TrainingRoomRepository (private val trainingRoomDao: TrainingRoomDao):
    TrainingDatabaseRepository {
    override val readAll: LiveData<List<Training>>
        get() = trainingRoomDao.getAllTraining()

    override fun getTrainingStart(trainingId: Int): LiveData<String> {
        return trainingRoomDao.getTrainingStart(trainingId)
    }

    override fun getTrainingByEverything(userId: Int,exerciseId: Int, dateStart:String): LiveData<List<Training>> {
        return trainingRoomDao.getTrainingByEverything(userId, exerciseId, dateStart)
    }

    override fun getTrainingByUserId(userId: Int): LiveData<List<Training>> {
        return trainingRoomDao.getTrainingByUserId(userId)
    }

    override suspend fun create(training: Training, onSuccess: () -> Unit) {
        trainingRoomDao.addTraining(training = training)
        onSuccess
    }

    override suspend fun update(training: Training, onSuccess: () -> Unit) {
        trainingRoomDao.updateTraining(training = training)
        onSuccess()
    }

    override suspend fun delete(training: Training, onSuccess: () -> Unit) {
        trainingRoomDao.deleteTraining(training = training)
        onSuccess()
    }

    override fun readTrainingAll(isPlay: Int): LiveData<List<Training>> {
        return trainingRoomDao.getTraining(isPlay)
    }
    }

