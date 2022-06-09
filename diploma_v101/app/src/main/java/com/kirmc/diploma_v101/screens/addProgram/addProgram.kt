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
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.navigation.NavRoute

@Composable
fun AddProgramScreen(navController: NavController, viewModel: MainViewModel, userId: String?) {
    var i: Int = 0
    if (userId != null) {
        i = userId?.toInt()
    }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
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
                                    navController.navigate(NavRoute.Programs.route + "/${i},${userId}")
                                }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = "Создать программу",
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
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            // horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Создание программы",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 9.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text(text = "Название программы") },
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = description,
                onValueChange = {
                    description = it
                },
                label = { Text(text = "Описание программы тренировок") },
            )
            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    viewModel.addPrograms(
                        programs = Programs(
                            name = name,
                            description = description,
                            userId = i,
                            groupId = 0
                        )
                    ) {
                    }
                    navController.navigate(NavRoute.Programs.route + "/${i},${userId}")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Создать",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 23.sp
                )
            }
        }
    }
}