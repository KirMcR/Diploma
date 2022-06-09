package com.kirmc.diploma_v101.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.MainViewModelFactory
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants.Keys.FIREBASE_DATABASE
import com.kirmc.diploma_v101.utils.Constants.Keys.ROOM_DATABASE
import com.kirmc.diploma_v101.utils.Constants.Keys.WHAT_WILL_WE_USE
//import com.kirmc.diploma_v101.utils.DB_TYPE
import com.kirmc.diploma_v101.utils.TYPE_FIREBASE
import com.kirmc.diploma_v101.utils.TYPE_ROOM

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(text = WHAT_WILL_WE_USE)
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_ROOM) {
                         navController.navigate(route = NavRoute.Login.route)
                    }

                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Начать пользоваться")
            }
            /*Button(
                onClick = {
                   // mViewModel.initDatabase(TYPE_FIREBASE) {
                        navController.navigate(route = NavRoute.SignIn.route)
                    //}

                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = FIREBASE_DATABASE)
            }*/

        }
    }
}