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
fun companyNavigationScreen(navController: NavHostController, viewModel: MainViewModel, group1Id:String?) {
    val context = LocalContext.current

    var companyId: Int = 0
    var groupId = 0

    var userId: Int = 0
    if (group1Id != null) {
        groupId = group1Id?.toInt()
    }
    var getId = viewModel.getCompanyId(groupId).observeAsState().value
    if (getId != null) {
        companyId = getId?.toInt()
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
                                .weight(0.25f)
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                modifier = Modifier.clickable {

                                    navController.navigate(route = NavRoute.Groups.route + "/${companyId}")

                                }
                            )
                        }
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
                        navController.navigate(NavRoute.cPrograms.route + "/${groupId}")
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
                        navController.navigate(NavRoute.cUsers.route + "/${groupId}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Пользователи",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp
                    )
                }
            }

        }
    }
}

