package com.kirmc.diploma_v101.screens.play

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.MainViewModelFactory
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants


@Composable
fun PlayExercisesScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    trainDayId: String?
) {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))



    var i: Int = 0

   /* if (trainDayId != null) {
        i = trainDayId?.toInt()
    }*/


    var userId =0
    if (trainDayId != null) {
        var myIndexes = trainDayId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}


    /*
    var companyId = 0
    var groupId = 0
    var userId =0
    var progId: Int = 0
    //var myIndexes: List<String> =
    if (trainDayId != null) {
        var myIndexes = trainDayId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        progId = myIndexes[1]?.toInt()
        userId = myIndexes[2]?.toInt()
        companyId = myIndexes[3]?.toInt()
        groupId = myIndexes[4]?.toInt()
    }*/

    val exercises1 = viewModel.showByTrainDaysId(i).observeAsState(listOf()).value
    val exercises = viewModel.readAllExercises().observeAsState(listOf()).value
    var exercise = Exercises(
        name = Constants.Keys.NONE,
        description = Constants.Keys.NONE,
      //  programId = 0,
        trainDayId = 0
    )
    for (tyu in exercises1) {
        if (tyu.id == i) {
            exercise = tyu
            break;
        }
    }

    Scaffold(
        backgroundColor = Color.LightGray,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 7.dp),
                        //horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = "Упражнения",
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontSize = 27.sp
                            )

                        }
                    }

                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = 9.dp
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 9.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {
        LazyColumn {
            items(exercises1) { exercises ->
                ExercisesItem(
                    exercises = exercises,
                    navController = navController,
                    viewModel = viewModel,
                    i = i,
                    userId = userId
                )

            }
        }
        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                val date = getCurrentDateTime()
                val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White)) {
            Text(text = "Стоп", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
        }
    }}
}

@Composable
fun ExercisesItem(
    exercises: Exercises,
    navController: NavHostController,
    viewModel: MainViewModel,
    i: Int,
    userId: Int
) {


    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
            .clickable {
                navController.navigate(NavRoute.PlayExercise.route + "/${exercises.id},${userId}")
            },

        elevation = 7.dp,

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