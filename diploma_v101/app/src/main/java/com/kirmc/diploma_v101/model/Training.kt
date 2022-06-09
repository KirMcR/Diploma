package com.kirmc.diploma_v101.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kirmc.diploma_v101.utils.Constants


@Entity(tableName = Constants.Keys.TRAINING_TABLE,
    /*foreignKeys = [
        androidx.room.ForeignKey(
            entity = Programs::class,
            parentColumns = ["id"],
            childColumns =["programId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        androidx.room.ForeignKey(
            entity = com.kirmc.diploma_v101.model.TrainDays::class,
            parentColumns = ["id"],
            childColumns = ["trainDayId"],
            onDelete = androidx.room.ForeignKey.CASCADE,
            onUpdate = androidx.room.ForeignKey.CASCADE
        ),
        androidx.room.ForeignKey(
            entity = com.kirmc.diploma_v101.model.Exercises::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = androidx.room.ForeignKey.CASCADE,
            onUpdate = androidx.room.ForeignKey.CASCADE
        ),
        androidx.room.ForeignKey(
            entity = com.kirmc.diploma_v101.model.Sets::class,
            parentColumns = ["id"],
            childColumns = ["setId"],
            onDelete = androidx.room.ForeignKey.CASCADE,
            onUpdate = androidx.room.ForeignKey.CASCADE
        )
    ]*/)
data class Training(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val dateStart:  String = "",
    @ColumnInfo
    val dateEnd:  String = "",
    @ColumnInfo
    val isActive:  Int = 0,
    @ColumnInfo
    val isStart:Int = 0,
    @ColumnInfo
    val setId:Int = 0,
    @ColumnInfo
    val exerciseId:Int = 0,
    @ColumnInfo
    val trainDayId:Int = 0,
    @ColumnInfo
    val programId:Int = 0,
    @ColumnInfo
    val userId:Int=0
)