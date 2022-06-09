package com.kirmc.diploma_v101

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
//import com.kirmc.diploma_v101.database.firebase.ProgramsFirebaseRepository
//import com.kirmc.diploma_v101.database.firebase.UsersFirebaseRepository
import com.kirmc.diploma_v101.database.room.AppRoomDataBase
import com.kirmc.diploma_v101.database.room.repository.*
import com.kirmc.diploma_v101.model.*
import com.kirmc.diploma_v101.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val daoPrograms =
                    AppRoomDataBase.getInstance(context = context).getProgramsRoomDao()
                PROGRAMS_REPOSITORY = PogramsRoomRepository(daoPrograms)

                val daoTrainDays =
                    AppRoomDataBase.getInstance(context = context).getTrainDaysRoomDao()
                TRAIN_DAYS_REPOSITORY = TrainDaysRoomRepository(daoTrainDays)

                val daoExercises =
                    AppRoomDataBase.getInstance(context = context).getExercisesRoomDao()
                EXERCISES_REPOSITORY = ExercisesRoomRepository(daoExercises)

                val daoSets =
                    AppRoomDataBase.getInstance(context = context).getSetsRoomDao()
                SETS_REPOSITORY = SetsRoomRepository(daoSets)

                val daoUsers =
                    AppRoomDataBase.getInstance(context = context).getUsersRoomDao()
                USERS_REPOSITORY = UsersRoomRepository(daoUsers)

                val daoTraining =
                    AppRoomDataBase.getInstance(context = context).getTrainingRoomDao()
                TRAINING_REPOSITORY = TrainingRoomRepository(daoTraining)

                val daoGroup =
                    AppRoomDataBase.getInstance(context = context).getGroupRoomDao()
                GROUP_REPOSITORY = GroupRoomRepository(daoGroup)
                onSuccess()
            }
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------

    fun addPrograms(programs: Programs, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            PROGRAMS_REPOSITORY.create(programs = programs) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updatePrograms(programs: Programs, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            PROGRAMS_REPOSITORY.update(programs = programs){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deletePrograms(programs: Programs, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            PROGRAMS_REPOSITORY.delete(programs = programs){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllPrograms() = PROGRAMS_REPOSITORY.readAll
//---------------------------------------------------------------------------------------------------------------------------------------------------------

    fun addUsers(users: Users, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            USERS_REPOSITORY.create(users = users) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateUsers(users: Users, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            USERS_REPOSITORY.update(users = users){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteUsers(users: Users, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            USERS_REPOSITORY.delete(users = users){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllUsers() = USERS_REPOSITORY.readAll
//---------------------------------------------------------------------------------------------------------------------------------------------------------

    fun addTraining(training: Training, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            TRAINING_REPOSITORY.create(training = training) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateTraining(training: Training, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            TRAINING_REPOSITORY.update(training = training){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteTraining(training: Training, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            TRAINING_REPOSITORY.delete(training = training){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllTraining() = TRAINING_REPOSITORY.readAll
//---------------------------------------------------------------------------------------------------------------------------------------------------------

    fun addTrainDays(trainDay: TrainDays, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            TRAIN_DAYS_REPOSITORY.create(trainDay = trainDay) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateTrainDays(trainDay: TrainDays, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            TRAIN_DAYS_REPOSITORY.update(trainDay = trainDay){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteTrainDays(trainDay: TrainDays, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            TRAIN_DAYS_REPOSITORY.delete(trainDay = trainDay){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllTrainDays() = TRAIN_DAYS_REPOSITORY.readAll

//---------------------------------------------------------------------------------------------------------------------------------------------------------
//Поиск в таблице ТРЕНИРОВОЧНЫЕ ДНИ
    fun showByProgramsId(programId: Int) : LiveData<List<TrainDays>> =
        TRAIN_DAYS_REPOSITORY.readProgramAll(programId)

    fun getProgramId(trainDayId: Int) : LiveData<Int> =
        TRAIN_DAYS_REPOSITORY.getProgramId(trainDayId)

    fun getTrainDayName(trainDayId: Int) : LiveData<String> =
        TRAIN_DAYS_REPOSITORY.getTrainDayName(trainDayId)

//---------------------------------------------------------------------------------------------------------------------------------------------------------
//Поиск в таблице ПОЛЬЗОВАТЕЛЕЙ
    fun showUsersByDirectorId(directorId: Int) : LiveData<List<Users>> =
        USERS_REPOSITORY.readUsersAll(directorId)

    fun getUserGroupId(userId: Int) : LiveData<Int> =
        USERS_REPOSITORY.getUserGroupId(userId)

    fun getUserCompanyId(userId: Int) : LiveData<Int> =
        USERS_REPOSITORY.getUserCompanyId(userId)

    fun showIsUserCompany(userId: Int) : LiveData<Int> =
        USERS_REPOSITORY.getIsCompanyId(userId)

    //Получение разных пользователей
    fun getModers() : LiveData<List<Users>> =
        USERS_REPOSITORY.getModers()
    fun getAdmins() : LiveData<List<Users>> =
        USERS_REPOSITORY.getAdmins()
    fun getCompanies() : LiveData<List<Users>> =
        USERS_REPOSITORY.getCompanies()
    fun getUsualUsers() : LiveData<List<Users>> =
        USERS_REPOSITORY.getUsualUsers()


//---------------------------------------------------------------------------------------------------------------------------------------------------------
//Поиск в таблице ПРОГРАММ
    fun showProgramsByGroup(groupId: Int): LiveData<List<Programs>> =
        PROGRAMS_REPOSITORY.readProgramsCompany(groupId)

    fun showByProgramsOnline() : LiveData<List<Programs>> =
        PROGRAMS_REPOSITORY.readOnlineProgramsAll()

    fun getAvailablePrograms() : LiveData<List<Programs>> =
        PROGRAMS_REPOSITORY.getAvailablePrograms()

    fun showByUserId(userId: Int) : LiveData<List<Programs>> =
        PROGRAMS_REPOSITORY.readProgramsAll(userId)

    fun getUserId(programId: Int) : LiveData<Int> =
        PROGRAMS_REPOSITORY.getUserId(programId)

    fun getGroupId(programId: Int) : LiveData<Int> =
        PROGRAMS_REPOSITORY.getGroupId(programId)

    fun getProgramName(programId: Int) : LiveData<String> =
        PROGRAMS_REPOSITORY.getProgramName(programId)

//---------------------------------------------------------------------------------------------------------------------------------------------------------
//Поиск в таблице ТРЕНИРОВКИ
    fun showByTrainingId(isPlay: Int):LiveData<List<Training>> =
        TRAINING_REPOSITORY.readTrainingAll(isPlay)

    fun getTrainingByUserId(userId: Int):LiveData<List<Training>> =
        TRAINING_REPOSITORY.getTrainingByUserId(userId)

    fun getTrainingStart(trainingId: Int):LiveData<String> =
        TRAINING_REPOSITORY.getTrainingStart(trainingId)

    fun getTrainingByEverything(userId:Int, exerciseId:Int, dateStart:String):LiveData<List<Training>> =
        TRAINING_REPOSITORY.getTrainingByEverything(userId, exerciseId, dateStart)

//---------------------------------------------------------------------------------------------------------------------------------------------------------
//Поиск в таблице УПРАЖНЕНИЙ
    fun showByTrainDaysId(trainDayId: Int) : LiveData<List<Exercises>> =
        EXERCISES_REPOSITORY.readTrainDayAll(trainDayId)

    fun getTrainDayId(exerciseId: Int) : LiveData<Int> =
        EXERCISES_REPOSITORY.getTrainDayId(exerciseId)

    fun getExercise(exerciseId: Int) : LiveData<Exercises> =
        EXERCISES_REPOSITORY.getExercise(exerciseId)

    fun getExercisesName(exerciseId: Int) : LiveData<String> =
        EXERCISES_REPOSITORY.getExercisesName(exerciseId)

    //---------------------------------------------------------------------------------------------------------------------------------------------------------
//Поиск в таблице ПОДХОДОВ
    fun showByExerciseId(exerciseId: Int): LiveData<List<Sets>> =
        SETS_REPOSITORY.readSetsAll(exerciseId)

    fun showSetsByDayId(trainDayId: Int): LiveData<List<Sets>> =
        SETS_REPOSITORY.readSetsByDayAll(trainDayId)

    fun getExerciseId(setId: Int): LiveData<Int> =
        SETS_REPOSITORY.getExerciseId(setId)

    fun getSetQuantity(setId: Int): LiveData<String> =
        SETS_REPOSITORY.getSetQuantity(setId)

    fun getSetWeight(setId: Int): LiveData<String> =
        SETS_REPOSITORY.getSetWeight(setId)

//---------------------------------------------------------------------------------------------------------------------------------------------------------
//Поиск в таблице ГРУПП
    fun showGroupByAdminId(adminId: Int) : LiveData<List<Group>> =
        GROUP_REPOSITORY.readGroupAll(adminId)

    fun getCompanyId(groupId: Int) : LiveData<Int> =
        GROUP_REPOSITORY.getCompanyId(groupId)

//---------------------------------------------------------------------------------------------------------------------------------------------------------

    fun addExercises(exercises: Exercises, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            EXERCISES_REPOSITORY.create(exercises = exercises) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateExercises(exercises: Exercises, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            EXERCISES_REPOSITORY.update(exercises = exercises){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteExercises(exercises: Exercises, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            EXERCISES_REPOSITORY.delete(exercises = exercises){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllExercises() = EXERCISES_REPOSITORY.readAll

//---------------------------------------------------------------------------------------------------------------------------------------------------------

    fun addGroup(group: Group, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            GROUP_REPOSITORY.create(group = group) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateGroup(group: Group, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            GROUP_REPOSITORY.update(group = group){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteGroup(group: Group, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            GROUP_REPOSITORY.delete(group = group){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllGroup() = GROUP_REPOSITORY.readAll


//---------------------------------------------------------------------------------------------------------------------------------------------------------

    fun addSets(sets: Sets, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            SETS_REPOSITORY.create(sets = sets) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateSets(sets: Sets, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            SETS_REPOSITORY.update(sets = sets){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteSets(sets: Sets, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            SETS_REPOSITORY.delete(sets = sets){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllSets() = SETS_REPOSITORY.readAll

//---------------------------------------------------------------------------------------------------------------------------------------------------------

}



class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((MainViewModel::class.java))) {
            return MainViewModel(application = application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}