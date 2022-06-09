package com.kirmc.diploma_v101.database.room.repository

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.database.ProgramsDatabaseRepository
import com.kirmc.diploma_v101.database.UsersDatabaseRepository
import com.kirmc.diploma_v101.database.room.dao.UsersRoomDao
import com.kirmc.diploma_v101.model.TrainDays

import com.kirmc.diploma_v101.model.Users

class UsersRoomRepository (private val usersRoomDao: UsersRoomDao):
    UsersDatabaseRepository {
    override val readAll: LiveData<List<Users>>
        get() = usersRoomDao.getAllUsers()

    override suspend fun create(users: Users, onSuccess: () -> Unit) {
        usersRoomDao.addUsers(users = users)
        onSuccess
    }

    override fun readUsersAll(directorId: Int): LiveData<List<Users>> {
        return usersRoomDao.getUsersDirector(directorId)    }

    override fun getUserGroupId(userId: Int): LiveData<Int> {
        return usersRoomDao.getUserGroupId(userId)
    }

    override fun getUserCompanyId(userId: Int): LiveData<Int> {
        return usersRoomDao.getUserCompanyId(userId)
    }

    override fun getIsCompanyId(userId: Int): LiveData<Int> {
        return usersRoomDao.getIsUserCompany(userId)
    }
    override suspend fun update(users: Users, onSuccess: () -> Unit) {
        usersRoomDao.updateUsers(users = users)
        onSuccess()
    }

    override suspend fun delete(users: Users, onSuccess: () -> Unit) {
        usersRoomDao.deleteUsers(users = users)
        onSuccess()
    }

    override fun getAdmins(): LiveData<List<Users>> {
        return usersRoomDao.getAdmins()
    }

    override fun getCompanies(): LiveData<List<Users>> {
        return usersRoomDao.getCompanies()
    }

    override fun getModers(): LiveData<List<Users>> {
        return usersRoomDao.getModers()
    }

    override fun getUsualUsers(): LiveData<List<Users>> {
        return usersRoomDao.getUsualUsers()
    }

}
