package com.kirmc.diploma_v101.screens

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
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.TrainDays
import com.kirmc.diploma_v101.model.Users
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.*


@Composable
fun RegistrationScreen(navController: NavController, viewModel: MainViewModel) {
    //val context = LocalContext.current
    //val trainDays = viewModel.readAllTrainDays().observeAsState(listOf()).value

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Вход в аккаунт", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =27.sp)
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
                        )
                    ) {

                    }
                    navController.navigate(NavRoute.Login.route )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)
            ) {
                Text(text = "Регистрация", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }



            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    viewModel.addUsers(
                        users = Users(
                            login = login,
                            password = password,
                            isCompany = 1
                        )
                    ) {

                    }
                    navController.navigate(NavRoute.Login.route )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)
            ) {
                Text(text = "Создать организацию", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }



            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    viewModel.addUsers(
                        users = Users(
                            login = login,
                            password = password,
                            isAdmin = 1
                        )
                    ) {

                    }
                    navController.navigate(NavRoute.Login.route )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)
            ) {
                Text(text = "Создать администратора", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }



            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    viewModel.addUsers(
                        users = Users(
                            login = login,
                            password = password,
                            isModer = 1,
                        )
                    ) {

                    }
                    navController.navigate(NavRoute.Login.route )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)
            ) {
                Text(text = "Создать модератора", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }

        }

    }
}