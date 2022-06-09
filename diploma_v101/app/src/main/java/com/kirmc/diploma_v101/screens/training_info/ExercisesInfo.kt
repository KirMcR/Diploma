package com.kirmc.diploma_v101.screens.training_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.model.Training
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.screens.addProgram.ExercisesItem
import java.lang.Math.abs



@Composable
fun ExercisesInfoScreen(navController: NavController, viewModel: MainViewModel, trainDayId:String?) {
    var trainingID = 0
    var trainDayID = 0
    var userID = 0
    if (trainDayId != null) {
        var myIndexes = trainDayId.split(",").toTypedArray();
        trainingID = myIndexes[0]?.toInt()
        userID = myIndexes[1]?.toInt()
        trainDayID = myIndexes[2]?.toInt()
    }
    val exercises1 = viewModel.showByTrainDaysId(trainDayID).observeAsState(listOf()).value
        // var myPrograms = mutableListOf<Training>()

//    for(i in programs){
//        if(i.dateStart!="")
//        {
//            myPrograms.add(i)
//        }
//    }
    Scaffold(
        backgroundColor = Color.LightGray,
        topBar = {
            TopAppBar(
                title = {
                    Row(  modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 7.dp),
                        // horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Box(
                            modifier = Modifier
                                .weight(0.15f)
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    //navController.navigate(route = NavRoute.companyNavigation.route + "/${groupId}")
                                }
                            )
                        }
                        Box(modifier = Modifier
                            .weight(1f)){
                            Text(text = "Программы тренировок", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =21.sp)
                        }
                    }
                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = 9.dp
            )
        },
    ) {

        LazyColumn {
            items(exercises1) { exercises ->
                ExercisesItem(
                    exercises = exercises,
                    navController = navController,
                    viewModel = viewModel,
                    trainingId = trainingID,
                    userId = userID
                )

            }
        }
    }
}

@Composable
fun ExercisesItem(
    exercises: Exercises,
    navController: NavController,
    viewModel: MainViewModel,
    trainingId: Int,
    userId: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
            .clickable {
                navController.navigate(NavRoute.SetsInfo.route + "/${trainingId},${userId},${exercises.id}")
            },

        elevation = 7.dp
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 7.dp),
            //  horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = 15.dp)) {
                Column(
                    modifier = Modifier.padding(vertical = 9.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Упражнение: ",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(

                            text = "${exercises.name}",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }
            }


        }
    }
}

