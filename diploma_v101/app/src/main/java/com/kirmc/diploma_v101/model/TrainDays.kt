package com.kirmc.diploma_v101.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kirmc.diploma_v101.utils.Constants
import com.kirmc.diploma_v101.utils.Constants.Keys.TRAIN_DAYS_TABLE

@Entity(
    tableName = TRAIN_DAYS_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Programs::class,
            parentColumns = ["id"],
            childColumns =["programId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class TrainDays(
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    @ColumnInfo
    val name:String,
    @ColumnInfo
    val description:String,
    @ColumnInfo
    val programId: Int
)
