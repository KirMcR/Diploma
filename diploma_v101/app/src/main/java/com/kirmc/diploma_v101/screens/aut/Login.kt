package com.kirmc.diploma_v101.screens.aut

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
import com.kirmc.diploma_v101.navigation.NavRoute

@Composable
fun LogInScreen(navController: NavController, viewModel: MainViewModel) {
    //val context = LocalContext.current
    val users = viewModel.readAllUsers().observeAsState(listOf()).value

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
            verticalArrangement = Arrangement.Center,

        )
        {
            Text(
                text = "Вход",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 9.dp),
                color = Color.Black
            )

                OutlinedTextField(
                    value = login,
                                        onValueChange = {
                        login = it
                    },
                    label = { Text(text = "login") }

                )

            Box(){

            }
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
                    for(i in users)
                    {
                        if(i.login==login && i.password == password)
                        { if(i.isAdmin==1){
                            navController.navigate(NavRoute.adminNavigation.route +"/${i.id}")
                        }
                        else if(i.isModer==1){
                            navController.navigate(NavRoute.mPrograms.route +"/${i.id}")
                        }
                            else if(i.isCompany==1){
                            navController.navigate(NavRoute.Groups.route +"/${i.id}")
                            }
                            else{
                            navController.navigate(NavRoute.Programs.route +"/${i.id}")}
                            }

                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)

            ) {
                Text(text = "Войти", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }
            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                     navController.navigate(NavRoute.Registration.route )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)

            ) {
                Text(text = "Регистрация", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }

        }

    }
}