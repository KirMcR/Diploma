package com.kirmc.diploma_v101.screens.admin

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
fun adminNavigationScreen(navController: NavHostController, viewModel: MainViewModel, adminID :String?) {
    val context = LocalContext.current

    var companyId: Int = 0
    var adminId = 0

    var userId: Int = 0
    if (adminID != null) {
        adminId = adminID?.toInt()
    }

    /*
    if(groupId!=null){
        var myIndexes = groupId.split(",").toTypedArray();
        companyId = myIndexes[0]?.toInt()
        group1Id = myIndexes[1]?.toInt()
    }*/

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
                                text = "Панель администратора",
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
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Box() {
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick =
                    {
                        navController.navigate(NavRoute.mPrograms.route + "/${adminId}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Программы тренировок",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp
                    )
                }
            }
            Box() {
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick =
                    {
                        navController.navigate(NavRoute.adminUsers.route + "/${adminId},${1}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Простые пользователи",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp
                    )
                }
            }
            Box() {
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick =
                    {
                        navController.navigate(NavRoute.adminUsers.route + "/${adminId},${2}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Корпоративные клиенты",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp
                    )
                }
            }
            Box() {
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick =
                    {
                        navController.navigate(NavRoute.adminUsers.route + "/${adminId},${3}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Модераторы",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp
                    )
                }
            }
            Box() {
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick =
                    {
                        navController.navigate(NavRoute.adminUsers.route + "/${adminId},${0}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Администраторы",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp
                    )
                }
            }
        }
    }
}


