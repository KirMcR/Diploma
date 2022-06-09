package com.kirmc.diploma_v101.database

import androidx.lifecycle.LiveData

import com.kirmc.diploma_v101.model.Training



interface TrainingDatabaseRepository {
    val readAll: LiveData<List<Training>>
    fun readTrainingAll(isPlay: Int): LiveData<List<Training>>
    fun getTrainingByUserId(userId: Int): LiveData<List<Training>>
    fun getTrainingStart(trainingId: Int): LiveData<String>
    fun getTrainingByEverything(userId:Int, exerciseId:Int, dateStart:String): LiveData<List<Training>>
    suspend fun create(training: Training, onSuccess: () -> Unit)
    suspend fun update(training: Training, onSuccess: () -> Unit)
    suspend fun delete(training: Training, onSuccess: () -> Unit)

    //  fun signOut(){}
    //fun connectToDatabase(onSuccess: () -> Unit, onFail:(String)->Unit){}
}
