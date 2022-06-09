package com.kirmc.diploma_v101.database

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.model.Group
import com.kirmc.diploma_v101.model.Programs

interface GroupDatabaseRepository {
    val readAll: LiveData<List<Group>>
    fun readGroupAll(adminId: Int): LiveData<List<Group>>
    fun getCompanyId(groupId: Int): LiveData<Int>
    suspend fun create(group: Group, onSuccess: () -> Unit)
    suspend fun update(group: Group, onSuccess: () -> Unit)
    suspend fun delete(group: Group, onSuccess: () -> Unit)
}