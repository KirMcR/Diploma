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
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.model.Training
import com.kirmc.diploma_v101.navigation.NavRoute
import java.lang.Math.abs

@Composable
fun ProgramsInfoScreen(navController: NavHostController, viewModel: MainViewModel, userId:String?) {
    var userID = 0
    if(userId!=null){
        userID = userId?.toInt()
    }
    var programs = viewModel.getTrainingByUserId(userID).observeAsState(listOf()).value
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
            items(programs) { program ->
                ProgramsItem(
                    program = program,
                    navController = navController,
                    viewModel = viewModel,
                    userId = userID
                )
            }
        }
    }
}

@Composable
fun ProgramsItem(
    program: Training,
    navController: NavHostController,
    viewModel: MainViewModel,
    userId: Int
) {
    if (program.dateEnd != "" && program.dateStart != "" && program.setId == 0) {
        val programName = viewModel.getProgramName(program.programId).observeAsState().value
        val trainDayName = viewModel.getTrainDayName(program.trainDayId).observeAsState().value
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 9.dp, horizontal = 13.dp)
                .clickable {
                    navController.navigate(NavRoute.ExercisesInfo.route + "/${program.id},${userId},${program.trainDayId}")
                },
            elevation = 7.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
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
                                text = "Программа: ",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${programName}",
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "День: ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${trainDayName}",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Продолжительность: ${
                                    durationTrain(
                                        program.dateStart,
                                        program.dateEnd
                                    )
                                } ",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Начало: ${program.dateStart} ",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
fun durationTrain(Start: String, End: String):String{
   // if (exerciseId != null) {}
        var myIndexes = Start.split(" ").toTypedArray();
        var start = myIndexes[1]?.toString()
        myIndexes = End.split(" ").toTypedArray();
        var end = myIndexes[1]?.toString()

        myIndexes = start.split(":").toTypedArray();
        var s1 = myIndexes[0]?.toInt()
        var m1 = myIndexes[1]?.toInt()
        var h1 = myIndexes[2]?.toInt()

    myIndexes = end.split(":").toTypedArray();
        var s2 = myIndexes[0]?.toInt()
        var m2 = myIndexes[1]?.toInt()
        var h2 = myIndexes[2]?.toInt()
    var sta = (h1*60+m1)*60 + s1
    var enD = (h2*60+m2)*60 + s2
    var time = abs(enD-sta)
    h1 = time/3600
    m1 = (time-h1*3600)/60
    s1 = (time-h1*3600-m1*60)
    var dur = "${s1}с ${m1}м ${h1}ч"
//    var dur = "${sta}нач ${enD}кон ${time} разница, НАЧАЛО: ${start}, КОНЕЦ: ${end}"



    return dur
}
