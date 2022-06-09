package com.kirmc.diploma_v101.screens.addProgram

import android.app.Application
import androidx.compose.foundation.background
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
import com.kirmc.diploma_v101.model.TrainDays
import com.kirmc.diploma_v101.navigation.NavRoute

@Composable
fun TrainDaysScreen(navController: NavHostController, viewModel: MainViewModel, programId:String?) {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    var companyId = 0
    var groupId = 0
    var userId =0
    var i:Int = 0

    if(programId!=null){
        var myIndexes = programId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}

    var isCompany = 0
    var getId = viewModel.showIsUserCompany(userId).observeAsState().value
    if (getId != null) {
        isCompany = getId
    }
    getId = viewModel.getGroupId(i).observeAsState().value
    if (getId != null) {
        groupId = getId
    }

    val trainDay1 = viewModel.showByProgramsId(i).observeAsState(listOf()).value
    val trainDay = viewModel.readAllTrainDays().observeAsState(listOf()).value

    Scaffold(
        backgroundColor = Color.LightGray,
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
                        Box(modifier = Modifier
                            .weight(0.25f)){
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                if(isCompany==0) {
                                    navController.navigate(route = NavRoute.Programs.route + "/${userId}")
                                }
                                    else
                                {
                                    navController.navigate(route = NavRoute.cPrograms.route + "/${groupId}")
                                }
                                }
                            )}
                        Box(modifier = Modifier
                            .weight(1f)){
                            Text(text = "Тренировочные дни ", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =27.sp)
                            }
                    }


                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = 9.dp
            )
        },
        floatingActionButton = {
         //   if(groupId ==0){
            FloatingActionButton(
                backgroundColor = Color.DarkGray,
                onClick = {
                    navController.navigate(NavRoute.AddTrainDay.route +  "/${i},${userId}")
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White
                )

            //}
        }}
    ) {

        LazyColumn {

                items(trainDay1) { trainDaY ->
                    TrainDayItem(
                        trainDaY = trainDaY,
                        navController = navController,
                        viewModel = mViewModel,
                        i =i,
                        userId = userId

                    )

                }

        }
    }
}

@Composable
fun TrainDayItem(trainDaY: TrainDays, navController: NavHostController, viewModel: MainViewModel, i:Int, userId:Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
            .clickable {
                navController.navigate(NavRoute.Exercises.route + "/${trainDaY.id},${userId}")
            },
        elevation = 7.dp
    ) {Row(  modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 7.dp),
        //  horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){ Box(modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .padding(start = 15.dp)) {
        Column(
            modifier = Modifier.padding(vertical = 9.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            )
        {
            Row(  modifier = Modifier
                .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text ="День: ",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(

                    text ="${trainDaY.name}",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold

                )
            }
            Row(  modifier = Modifier
                .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Описание: ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${trainDaY.description}", fontSize = 15.sp)
            }
        }
    }

            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigate(NavRoute.UpdateTrainDay.route + "/${trainDaY.id},${userId}")
                    }

                )
            }
            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        viewModel.deleteTrainDays(trainDay = trainDaY) {
                            navController.navigate(NavRoute.TrainDays.route + "/${i},${userId}")
                        }
                    }

                )
            }
        }
    }


       /* Column(
            modifier = Modifier.padding(vertical = 9.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = trainDaY.name, fontSize = 21.sp, fontWeight = FontWeight.Bold
            )
            Text(text = trainDaY.description, fontSize = 15.sp)

            Button(onClick = {
                viewModel.deleteTrainDays(trainDay = trainDaY){
                    navController.navigate(NavRoute.TrainDays.route+  "/${i}")
                }
            }) {
                Text(text = "DELETE")

            }
            Button(onClick = {
                navController.navigate(NavRoute.UpdateTrainDay.route+ "/${trainDaY.id}")




            }) {
                Text(text = "UPDATE")

            }
        }*/
    }
