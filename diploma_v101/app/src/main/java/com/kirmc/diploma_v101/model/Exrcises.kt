package com.kirmc.diploma_v101.model

import androidx.room.*
import com.kirmc.diploma_v101.utils.Constants.Keys.EXERCISES_TABLE


@Entity(tableName = EXERCISES_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = TrainDays::class,
            parentColumns = ["id"],
            childColumns =["trainDayId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ])
data class Exercises
    (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val trainDayId: Int,
)