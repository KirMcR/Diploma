package com.kirmc.diploma_v101.database.room.repository

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.database.TrainDaysDatabaseRepository
import com.kirmc.diploma_v101.database.room.dao.TrainDaysRoomDao
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.model.TrainDays

class TrainDaysRoomRepository(private val trainDayRoomDao: TrainDaysRoomDao) :
    TrainDaysDatabaseRepository {
    override fun readProgramAll(programId: Int): LiveData<List<TrainDays>> {
        return trainDayRoomDao.getProgramsTrainDay(programId)

    }

    override val readAll: LiveData<List<TrainDays>>
        get() = trainDayRoomDao.getAllTrainDay()

    override fun getTrainDayName(trainDayId: Int): LiveData<String> {
        return trainDayRoomDao.getTrainDayName(trainDayId)
    }

    override suspend fun create(trainDay: TrainDays, onSuccess: () -> Unit) {
        trainDayRoomDao.addTrainDay(trainDay = trainDay)
        onSuccess
    }

    override suspend fun update(trainDay: TrainDays, onSuccess: () -> Unit) {
        trainDayRoomDao.updateTrainDay(trainDay = trainDay)
        onSuccess()
    }

    override suspend fun delete(trainDay: TrainDays, onSuccess: () -> Unit) {
        trainDayRoomDao.deleteTrainDay(trainDay = trainDay)
        onSuccess()
    }

    override fun getProgramId(trainDayId: Int): LiveData<Int> {
        return trainDayRoomDao.getProgramId(trainDayId)
    }
}