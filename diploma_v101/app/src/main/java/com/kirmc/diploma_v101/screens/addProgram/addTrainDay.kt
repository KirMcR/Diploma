package com.kirmc.diploma_v101.screens.addProgram

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.model.TrainDays
import com.kirmc.diploma_v101.navigation.NavRoute

@Composable
fun AddTrainDayScreen(navController: NavController, viewModel: MainViewModel, programId:String?) {
    //val context = LocalContext.current
    //val progId by remember{ mutableStateOf(programId)}
    var i:Int = 0
   /* var companyId = 0
    var groupId = 0*/
    var userId =0
    if (programId != null) {
        var myIndexes = programId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}

    /*if (programId != null) {
        i = programId?.toInt()
    }*/
    /*
   if(programId!=null){
       var myIndexes = programId.split(",").toTypedArray();
       i = myIndexes[0]?.toInt()
       userId = myIndexes[1]?.toInt()
       companyId = myIndexes[2]?.toInt()
       groupId = myIndexes[3]?.toInt()
    }*/
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(  modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 3.dp),
                    //horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                           .weight(0.1f)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                navController.navigate(NavRoute.TrainDays.route+  "/${i},${userId}")
                            }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Создать тренировочный дeнь",
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
           // horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Создание тренировочного дня",
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
                    viewModel.addTrainDays(
                        trainDay = TrainDays(
                            name = name,
                            description = description,
                            programId = i


                        )
                    ) {

                    }
                    navController.navigate(NavRoute.TrainDays.route+  "/${i},${userId}")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)
            ) {
                Text(text = "Создать", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }

        }

    }
}