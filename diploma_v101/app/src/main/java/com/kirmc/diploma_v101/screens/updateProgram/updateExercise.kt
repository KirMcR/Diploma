package com.kirmc.diploma_v101.screens.updateProgram

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.ColumnInfo
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.model.TrainDays
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants


@Composable
fun UpdateExerciseScreen(navController: NavController, viewModel: MainViewModel, exerciseId:String?) {
    //val context = LocalContext.current
    val exercises = viewModel.readAllExercises().observeAsState(listOf()).value
    var exercise: Exercises = Exercises(
        name = Constants.Keys.NONE,
        description = Constants.Keys.NONE,
        trainDayId = 0,

    )
    var i:Int = 0


    var userId =0
    if (exerciseId != null) {
        var myIndexes = exerciseId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}

//    if (exerciseId != null) {
//        i = exerciseId?.toInt()
//    }
    /*
    var userId =0
    var companyId = 0
    var groupId = 0

    if(exerciseId!=null){
        var myIndexes = exerciseId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()
        companyId = myIndexes[2]?.toInt()
        groupId = myIndexes[3]?.toInt()
    }

*/
    for (pro in exercises){
        if(pro.id == i){
            exercise = pro
            break
        }
    }
    var name by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var description by remember { mutableStateOf(Constants.Keys.EMPTY) }
    name = exercise.name
    description = exercise.description

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Изменение упражнения",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp)
                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = 9.dp
            )
        },
    )
    {
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
        //    horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Изменение упражнения",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 9.dp)
            )
            OutlinedTextField(
                modifier=Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it

                },
                label = { Text(text = "Название упражнения") },
            )
            OutlinedTextField(
                modifier=Modifier.fillMaxWidth().weight(1f),
                value = description,
                onValueChange = {
                    description = it
                },
                label = { Text(text = "Описание упражнения") },

                )
            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    viewModel.updateExercises(
                        exercises = Exercises(
                            id = exercise.id,
                            name = name,
                            description = description,
                            trainDayId = exercise.trainDayId

                        )
                    ) {

                    }
                    navController.navigate(NavRoute.Exercises.route+  "/${exercise.trainDayId}${userId}")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)
            ) {
                Text(text = "Обновить", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }

        }

    }
}