package com.kirmc.diploma_v101.database.room.repository

import androidx.lifecycle.LiveData
import com.kirmc.diploma_v101.database.ProgramsDatabaseRepository
import com.kirmc.diploma_v101.database.room.dao.ProgramsRoomDao
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.Programs

class PogramsRoomRepository(private val programsRoomDao: ProgramsRoomDao) :
    ProgramsDatabaseRepository {
    override val readAll: LiveData<List<Programs>>
        get() = programsRoomDao.getAllPrograms()

    override fun getProgramName(programId: Int): LiveData<String> {
        return programsRoomDao.getProgramName(programId)
    }

    override fun getAvailablePrograms(): LiveData<List<Programs>> {
        return programsRoomDao.getAvailablePrograms()
    }

    override fun readProgramsAll(userId: Int): LiveData<List<Programs>> {
        return programsRoomDao.getPrograms(userId)
    }

    override fun readProgramsCompany(groupId: Int): LiveData<List<Programs>> {
        return programsRoomDao.getProgramsCompany(groupId)
    }

    override fun readOnlineProgramsAll(): LiveData<List<Programs>> {
        return programsRoomDao.getOnlinePrograms()
    }

    override suspend fun create(programs: Programs, onSuccess: () -> Unit) {
        programsRoomDao.addProgram(programs = programs)
        onSuccess
    }

    override suspend fun update(programs: Programs, onSuccess: () -> Unit) {
        programsRoomDao.updateProgram(programs = programs)
        onSuccess()
    }

    override suspend fun delete(programs: Programs, onSuccess: () -> Unit) {
        programsRoomDao.deleteProgram(programs = programs)
        onSuccess()
    }

    override fun getGroupId(programId: Int): LiveData<Int> {
        return programsRoomDao.getGroupId(programId)
    }

    override fun getUserId(programId: Int): LiveData<Int> {
        return programsRoomDao.getUserId(programId)
    }
}