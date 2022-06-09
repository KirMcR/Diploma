package com.kirmc.diploma_v101.screens.admin

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.room.ColumnInfo
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.TrainDays
import com.kirmc.diploma_v101.model.Users
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.*


@Composable
fun addUserScreen(navController: NavController, viewModel: MainViewModel, adminID: String?) {
    //val context = LocalContext.current
    //val trainDays = viewModel.readAllTrainDays().observeAsState(listOf()).value

    var adminId:Int = 0
    var whatU = 0
    var isCompany: Int = 0
    var isAdmin: Int = 0
    var isModer: Int = 0
    var bar ="пользователя"

    if(adminID!=null){
        var myIndexes = adminID.split(",").toTypedArray();
        adminId = myIndexes[0]?.toInt()
        whatU = myIndexes[1]?.toInt()
    }

//  var users = viewModel.readAllUsers().observeAsState(listOf()).value
    if (whatU == 0) {
        isAdmin = 1
        bar ="администратора"
    } else if (whatU == 1) {
       // users = viewModel.getUsualUsers().observeAsState(listOf()).value
    } else if (whatU == 2) {
        isCompany = 1
        bar ="организацию"
    } else if (whatU == 3) {
        isModer = 1
        bar ="модератора"
    }

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Добавить ${bar}",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 27.sp
                    )
                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = 9.dp
            )
        },
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Вход",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = login,
                onValueChange = {
                    login = it

                },
                label = { Text(text = "login") },
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(text = "password") },

                )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(text = "password") },

                )
            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    viewModel.addUsers(
                        users = Users(
                            login = login,
                            password = password,
                            groupId = 0,
                            isCompany = isCompany,
                            isAdmin = isAdmin,
                            isModer = isModer
                        )
                    ) {

                    }
                    navController.navigate(NavRoute.adminUsers.route+"/${adminId},${whatU}")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Создать пользователя",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 23.sp
                )
            }
        }

    }

}

