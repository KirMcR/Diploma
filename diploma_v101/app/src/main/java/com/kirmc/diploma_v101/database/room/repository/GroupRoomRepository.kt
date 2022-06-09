package com.kirmc.diploma_v101.database.room.repository

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.database.ExercisesDatabaseRepository
import com.kirmc.diploma_v101.database.GroupDatabaseRepository
import com.kirmc.diploma_v101.database.ProgramsDatabaseRepository
import com.kirmc.diploma_v101.database.room.dao.ExercisesRoomDao
import com.kirmc.diploma_v101.database.room.dao.GroupRoomDao
import com.kirmc.diploma_v101.database.room.dao.ProgramsRoomDao
import com.kirmc.diploma_v101.model.Group
import com.kirmc.diploma_v101.model.Programs

class GroupRoomRepository(private val groupRoomDao: GroupRoomDao):
    GroupDatabaseRepository {
    override val readAll: LiveData<List<Group>>
        get() = groupRoomDao.getAllGroup()

    override fun readGroupAll(adminId: Int): LiveData<List<Group>> {
        return groupRoomDao.getGroup(adminId)
    }

    override suspend fun create(group: Group, onSuccess: () -> Unit) {
        groupRoomDao.addGroup(group = group)
        onSuccess
    }

    override suspend fun update(group: Group, onSuccess: () -> Unit) {
        groupRoomDao.updateGroup(group = group)
        onSuccess()
    }

    override suspend fun delete(group: Group, onSuccess: () -> Unit) {
        groupRoomDao.deleteGroup(group = group)
        onSuccess()
    }

    override fun getCompanyId(groupId: Int): LiveData<Int> {
        return groupRoomDao.getCompanyId(groupId)
    }

}
