package com.kirmc.diploma_v101.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kirmc.diploma_v101.database.room.dao.*
import com.kirmc.diploma_v101.model.*

import com.kirmc.diploma_v101.utils.Constants.Keys.MAIN_DATABASE

@Database(entities = [Exercises::class, Programs::class, TrainDays::class, Users::class, Sets::class, Training::class, Group::class], version = 1)
abstract class AppRoomDataBase: RoomDatabase() {

    abstract fun getExercisesRoomDao():ExercisesRoomDao
    abstract fun getProgramsRoomDao(): ProgramsRoomDao
    abstract fun getTrainDaysRoomDao(): TrainDaysRoomDao
    abstract fun getSetsRoomDao(): SetsRoomDao
    abstract fun getUsersRoomDao(): UsersRoomDao
    abstract fun getGroupRoomDao(): GroupRoomDao
    abstract fun getTrainingRoomDao(): TrainingRoomDao


    companion object{
        @Volatile
        private var INSTANCE: AppRoomDataBase? = null

        fun getInstance(context: Context):AppRoomDataBase{
            return if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDataBase::class.java,
                    MAIN_DATABASE
                ).build()
                INSTANCE as AppRoomDataBase
            }
            else INSTANCE as AppRoomDataBase

        }
    }
}