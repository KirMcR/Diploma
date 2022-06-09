package com.kirmc.diploma_v101.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kirmc.diploma_v101.utils.Constants.Keys.PROGRAMS_TABLE

@Entity(tableName = PROGRAMS_TABLE)
data class Programs(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String = "",
    @ColumnInfo
    val description: String = "",
    @ColumnInfo
    val userId: Int = 0,
    @ColumnInfo
    val isPublic: Int = 0,
    @ColumnInfo
    val isAvailable: Int = 0,
    @ColumnInfo
    val groupId: Int = 0

)