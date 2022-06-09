package com.kirmc.diploma_v101.screens.addProgram

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.kirmc.diploma_v101.model.TrainDays
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants

@Composable
fun AddExerciseScreen(navController: NavController, viewModel: MainViewModel, trainDayId:String?) {
    //val context = LocalContext.current
    val trainDays = viewModel.readAllTrainDays().observeAsState(listOf()).value

    var i:Int = 0

    var userId =0
    if (trainDayId != null) {
        var myIndexes = trainDayId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}

/*
    if (trainDayId != null) {
        i = trainDayId?.toInt()
    }*/
    /*
    var companyId = 0
    var groupId = 0
    var userId =0

    if(trainDayId!=null){
        var myIndexes = trainDayId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()
        companyId = myIndexes[2]?.toInt()
        groupId = myIndexes[3]?.toInt()
    }*/
    val trainDay = trainDays.firstOrNull{it.id == i}?: TrainDays(
        name = Constants.Keys.NONE,
        description = Constants.Keys.NONE,
        programId = 0
    )
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(  modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 7.dp),
                        //horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Box(
                            modifier = Modifier
                                .weight(0.25f)
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    navController.navigate(NavRoute.Exercises.route+  "/${i},${userId}")
                                }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = "Создать упражнение",
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontSize = 23.sp
                            )
                        }
                    }
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
            horizontalAlignment = Alignment.CenterHorizontally,
           // verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Создание упражнения",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 9.dp),
                color = Color.Black
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
                    viewModel.addExercises(
                        exercises = Exercises(
                            name = name,
                            description = description,
                            trainDayId = i
                        )
                    ) {

                    }
                    navController.navigate(NavRoute.Exercises.route+  "/${i},${userId}")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)

            ) {
                Text(text = "Создать", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }

        }

    }
}