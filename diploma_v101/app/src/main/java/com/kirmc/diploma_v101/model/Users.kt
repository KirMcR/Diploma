package com.kirmc.diploma_v101.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kirmc.diploma_v101.utils.Constants.Keys.USERS_TABLE

@Entity(tableName = USERS_TABLE)
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val login: String = "",
    @ColumnInfo
    val password: String = "",
    //  val firebaseId: String = ""
    @ColumnInfo
    val companyId: Int = 0,
    @ColumnInfo
    val isCompany: Int = 0,
    @ColumnInfo
    val isAdmin: Int = 0,
    @ColumnInfo
    val isModer: Int = 0,
    @ColumnInfo
    val groupId: Int = 0,
)