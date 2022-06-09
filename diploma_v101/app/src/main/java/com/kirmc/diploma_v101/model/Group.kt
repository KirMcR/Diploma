package com.kirmc.diploma_v101.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kirmc.diploma_v101.utils.Constants

@Entity(tableName = Constants.Keys.GROUP_TABLE)
data class Group(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String = "",
    @ColumnInfo
    val companyId: Int = 0

)