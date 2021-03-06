package com.kirmc.diploma_v101.screens.addProgram

import android.app.Application
import android.view.Menu
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants
//import com.kirmc.diploma_v101.utils.DB_TYPE
import com.kirmc.diploma_v101.utils.TYPE_FIREBASE
import com.kirmc.diploma_v101.utils.TYPE_ROOM
import kotlinx.coroutines.launch

@Composable
fun ProgramsScreen(navController: NavHostController, viewModel: MainViewModel, userId:String?) {
    var i:Int = 0
    var groupId = 0
    if(userId!=null){
        var myIndexes = userId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()}
    val gr1Id = viewModel.getUserGroupId(i).observeAsState().value
    if (gr1Id != null) {
        groupId = gr1Id
    }
    var programs = viewModel.showByUserId(i).observeAsState(listOf()).value
    if(groupId!=0){
        programs = viewModel.showProgramsByGroup(groupId).observeAsState(listOf()).value
    }
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
                        Box(modifier = Modifier.weight(0.15f)){
                            Icon(imageVector = Icons.Default.Info,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    navController.navigate(NavRoute.ProgramsInfo.route+"/${i}" )
                                }
                            )
                        }
                        Box(modifier = Modifier.weight(0.15f)){
                            Icon(imageVector = Icons.Default.Search,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    navController.navigate(route = NavRoute.sPrograms.route+"/${userId}")
                                }
                            )
                        }
                        Box(modifier = Modifier
                            .weight(1f)){
                            Text(text = "?????????????????? ", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =21.sp)
                        }
                        Box(modifier = Modifier.weight(0.15f)){
                            Icon(imageVector = Icons.Default.ExitToApp,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    navController.navigate(route = NavRoute.Main.route)
                                }
                            )
                        }
                        }
                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = 9.dp
            )
        },

        floatingActionButton = {
            if(groupId ==0){
            FloatingActionButton(
                backgroundColor = Color.DarkGray,
                onClick = {
                    navController.navigate(NavRoute.AddProgram.route + "/${i}")
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White,
                )

            }
        }
        },
        ) {

        LazyColumn {
            items(programs) { program ->
                ProgramsItem(
                    program = program,
                    navController = navController,
                    viewModel = viewModel,
                    userId=i
                )

            }
        }
    }
}

@Composable
fun ProgramsItem(
    program: Programs,
    navController: NavHostController,
    viewModel: MainViewModel,
    userId: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
            .clickable {
                navController.navigate(NavRoute.TrainDays.route + "/${program.id},${userId}")
            },
        elevation = 7.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 3.dp),
            //  horizontalArrangement = Arrangement.Center,
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
                            text = "??????????????????: ",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${program.name}",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "????????????????: ",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "${program.description}", fontSize = 15.sp)
                    }
                }
            }


            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigate(NavRoute.UpdateProgram.route + "/${program.id},${userId}")
                    }

                )
            }
            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        viewModel.deletePrograms(programs = program) {
                            navController.navigate(NavRoute.Programs.route + "/${userId}")
                        }
                    }

                )
            }
            Box() {
                Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            viewModel.updatePrograms(
                                programs = Programs(
                                    id = program.id,
                                    name = program.name,
                                    description = program.description,
                                    userId = program.userId,
                                    isPublic = 1
                                )
                            ) {
                                navController.navigate(NavRoute.Programs.route + "/${userId}")

                            }

                        }
                    )
                }
            }

        }
    }
}