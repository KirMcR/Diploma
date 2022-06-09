package com.kirmc.diploma_v101.database.room.repository

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.database.ExercisesDatabaseRepository
import com.kirmc.diploma_v101.database.room.dao.ExercisesRoomDao
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.TrainDays

class ExercisesRoomRepository (private val exercisesRoomDao: ExercisesRoomDao):
    ExercisesDatabaseRepository {
    override val readAll: LiveData<List<Exercises>>
        get() = exercisesRoomDao.getAllExercises()

    override fun readTrainDayAll(trainDayId: Int): LiveData<List<Exercises>> {
        return exercisesRoomDao.getTrainDaysExercise(trainDayId)
    }

    override suspend fun create(exercises: Exercises, onSuccess: () -> Unit) {
        exercisesRoomDao.addExercises(exercises = exercises)
        onSuccess
    }

    override suspend fun update(exercises: Exercises, onSuccess: () -> Unit) {
        exercisesRoomDao.updateExercises(exercises = exercises)
        onSuccess()
    }

    override suspend fun delete(trainDay: Exercises, onSuccess: () -> Unit) {
        exercisesRoomDao.deleteExercises(exercises = trainDay)
        onSuccess()
    }

    override fun getTrainDayId(exerciseId: Int): LiveData<Int> {
        return exercisesRoomDao.getTrainDayId(exerciseId)
    }

    override fun getExercisesName(exerciseId: Int): LiveData<String> {
        return exercisesRoomDao.getExercisesName(exerciseId)
    }

    override fun getExercise(exerciseId: Int): LiveData<Exercises> {
        return exercisesRoomDao.getExercise(exerciseId)
    }
}