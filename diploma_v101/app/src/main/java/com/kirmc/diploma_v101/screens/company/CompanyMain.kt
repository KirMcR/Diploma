package com.kirmc.diploma_v101.screens.company

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
fun CompanyMainScreen(navController: NavHostController, viewModel: MainViewModel, user1Id:String?) {
    val context = LocalContext.current
    var adminId:Int = 0
    if (user1Id != null) {
        var myIndexes = user1Id.split(",").toTypedArray();
        adminId = myIndexes[0]?.toInt()
    }
    val scope = rememberCoroutineScope()
    //  val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Row(  modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 7.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Box(modifier = Modifier.weight(0.15f)){
                            Icon(imageVector = Icons.Default.Search,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                              //TODO
                                }
                            )
                        }
                        Text("", modifier = Modifier.weight(1f))
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
                        navController.navigate(NavRoute.Groups.route+"/${adminId}" )
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
                        navController.navigate(NavRoute.cUsers.route+"/${adminId}" )
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)) {
                    Text(text="Пользователи", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
                }
            }

        }
    }
}

