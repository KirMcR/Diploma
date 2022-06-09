package com.kirmc.diploma_v101.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kirmc.diploma_v101.utils.Constants


@Entity(tableName = Constants.Keys.SETS_TABLE,
        foreignKeys = [
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
            )
])
data class Sets(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val quantity:  String = "",
    @ColumnInfo
    val weight:  String = "",
    @ColumnInfo
    val isActive: Int=0,
    @ColumnInfo
    val exerciseId: Int=0,
    @ColumnInfo
    val trainDayId: Int=0
)