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
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.model.TrainDays
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants


@Composable
fun UpdateTrainDayScreen(navController: NavController, viewModel: MainViewModel, trainDayId:String?) {
    //val context = LocalContext.current
    //val progId by remember{ mutableStateOf(programId)}
    val trainDays = viewModel.readAllTrainDays().observeAsState(listOf()).value
    var i:Int = 0

    var userId =0
    if (trainDayId != null) {
        var myIndexes = trainDayId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}

//    if (trainDayId != null) {
//        i = trainDayId?.toInt()
//    }
    /*
    var userId = 0
    var companyId = 0
    var groupId = 0

    if(trainDayId!=null){
        var myIndexes = trainDayId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()
        companyId = myIndexes[2]?.toInt()
        groupId = myIndexes[3]?.toInt()
    }
*/
    var trainDay: TrainDays = TrainDays(
        name = Constants.Keys.NONE,
        description = Constants.Keys.NONE,
        programId = i
    )
    for (pro in trainDays){
        if(pro.id == i){
            trainDay = pro
            break
        }
    }
    var name by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var description by remember { mutableStateOf(Constants.Keys.EMPTY) }
    name = trainDay.name
    description = trainDay.description

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Изменение тренировочного дня ",
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
       //     horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Добавление тренировочного дня",
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
                label = { Text(text = "Название тренировочного дня") },
            )
            OutlinedTextField(
                modifier=Modifier.fillMaxWidth().weight(1f),
                value = description,
                onValueChange = {
                    description = it
                },
                label = { Text(text = "Описание тренировочного дня") },

                )
            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    viewModel.updateTrainDays(
                        trainDay = TrainDays(
                            id= trainDay.id,
                            name = name,
                            description = description,
                            programId = trainDay.programId


                        )
                    ) {

                    }
                    navController.navigate(NavRoute.TrainDays.route+  "/${trainDay.programId},${userId}")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)
            ) {
                Text(text = "Обновить", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }

        }

    }
}