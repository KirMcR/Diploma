package com.kirmc.diploma_v101.utils

import androidx.compose.runtime.mutableStateOf
import com.kirmc.diploma_v101.database.*

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"


lateinit var PROGRAMS_REPOSITORY: ProgramsDatabaseRepository
lateinit var SETS_REPOSITORY: SetsDatabaseRepository
lateinit var TRAIN_DAYS_REPOSITORY: TrainDaysDatabaseRepository
lateinit var EXERCISES_REPOSITORY: ExercisesDatabaseRepository
lateinit var TRAINING_REPOSITORY: TrainingDatabaseRepository
lateinit var GROUP_REPOSITORY: GroupDatabaseRepository
lateinit var USERS_REPOSITORY: UsersDatabaseRepository

var DB_TYPE = mutableStateOf("")

const val FIREBASE_ID = "firebaseId"

lateinit var LOGIN: String
lateinit var PASSWORD: String

object Constants{
    object Keys {
        const val NAME="name"
        const val DESCRIPTION = "description"

        const val TRAINING_TABLE="training_table"

        const val GROUP_TABLE="group_table"

        const val SETS_TABLE="sets_table"

        const val USERS_TABLE ="users_table"

        const val EXERCISES_TABLE = "exercises_table"

        const val TRAIN_DAYS_TABLE = "train_days_table"

        const val PROGRAMS_TABLE = "programs_table"

        const val PROGRAM_ID ="programId"

        const val MAIN_DATABASE = "main_database"

        const val SHOW_TRAIN_DAY = "show_programs"
        const val SHOW_PROGRAMS = "show_train_day"


        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_TITLE = "Note title"
        const val NOTE_SUBTITLE = "Note subtitle"
        const val ADD_NOTE = "Add note"
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
        const val WHAT_WILL_WE_USE = "What will we use?"

        const val ROOM_DATABASE = "Room database"
        const val FIREBASE_DATABASE = "Firebase database"
        const val ID = "Id"
        const val NONE = "none"
        const val UPDATE = "update"
        const val DELETE = "delete"
        const val NAV_BACK = "nav_back"
        const val EDIT_NOTE = "Edit note"
        const val EMPTY = ""
        const val UPDATE_NOTE = "Update note"

    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}