package com.kirmc.diploma_v101.screens

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
import androidx.navigation.NavHostController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.navigation.NavRoute


@Composable
fun NavigationScreen(navController: NavHostController, viewModel: MainViewModel, userId:String?) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    //  val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(

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
                                text = "Группы",
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
            modifier = Modifier.fillMaxSize().padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Box(){
                Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    navController.navigate(NavRoute.Programs.route+"/${userId}" )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)) {
                Text(text="Программы тренировок", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }
            }
            Box(){
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick =
                    {
                        navController.navigate(NavRoute.Programs.route+"/${userId}" )
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)) {
                    Text(text="Пользователи", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
                }
            }

    }
    }
}

@Composable
fun ProgramsItem(program: Programs, navController: NavHostController, viewModel: MainViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
            .clickable {
                navController.navigate(NavRoute.TrainDays.route + "/${program.id}")

            },

        elevation = 7.dp
    ) {


        Row(  modifier = Modifier
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
                        text ="Программа: ",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(

                        text ="${program.name}",
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
                    Text(text = "${program.description}", fontSize = 15.sp)
                }
            }
        }
            Box(modifier = Modifier.padding(start = 5.dp,end = 5.dp)){
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigate(NavRoute.UpdateProgram.route+ "/${program.id}")
                    }

                )
            }
            Box(modifier = Modifier.padding(start = 5.dp,end = 5.dp)){
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        viewModel.deletePrograms(programs = program){
                            navController.navigate(NavRoute.Programs.route)
                        }
                    }

                )
            }
        }




    }
}