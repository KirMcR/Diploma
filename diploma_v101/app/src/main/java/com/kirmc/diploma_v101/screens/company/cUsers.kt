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
import com.kirmc.diploma_v101.model.Users
import com.kirmc.diploma_v101.navigation.NavRoute
//import com.kirmc.diploma_v101.utils.DB_TYPE

@Composable
fun cUsersScreen(navController: NavHostController, viewModel: MainViewModel, group1Id:String?) {
    val context = LocalContext.current
    var companyId:Int = 0
    var groupId =0
    var userId:Int = 0

    if (group1Id != null) {
        groupId = group1Id?.toInt()
    }
    val getId = viewModel.getCompanyId(groupId).observeAsState().value
    if (getId != null) {
        companyId = getId?.toInt()
    }
    /*
    if(groupId!=null){
        var myIndexes = groupId.split(",").toTypedArray();
        companyId = myIndexes[0]?.toInt()
        group1Id = myIndexes[1]?.toInt()
    }*/
    val users = viewModel.showUsersByDirectorId(groupId).observeAsState(listOf()).value

    //val programs = viewModel.showByUserId(userId).observeAsState(listOf()).value
    val scope = rememberCoroutineScope()
    //  val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
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
                        Box(
                            modifier = Modifier
                                .weight(0.25f)
                        ) {
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    navController.navigate(route = NavRoute.companyNavigation.route + "/${groupId}")
                                }
                            )
                        }
                        Box(modifier = Modifier.weight(0.15f)){
                            Icon(imageVector = Icons.Default.Home,
                                contentDescription = "",
                                modifier = Modifier.clickable {

                                    navController.navigate(route = NavRoute.Main.route)

                                }
                            )
                        }
                        Box(modifier = Modifier.weight(0.15f)){
                            Icon(imageVector = Icons.Default.Search,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    navController.navigate(route = NavRoute.sPrograms.route+"/${companyId}")
                                }
                            )
                        }
                        Box(modifier = Modifier
                            .weight(1f)){
                            Text(text = "Пользователи", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =21.sp)
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
            FloatingActionButton(

                backgroundColor = Color.DarkGray,
                onClick = {
                    navController.navigate(NavRoute.addUserCompany.route+"/${groupId}" )
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White,


                    )

            }

        },


        ) {

        LazyColumn {
            items(users) { user ->
                UsersItem(
                    user = user,
                    navController = navController,
                    viewModel = viewModel,
                    companyId = companyId,
                    groupId = groupId
                )

            }
        }
    }
}

@Composable
fun UsersItem(user: Users, navController: NavHostController, viewModel: MainViewModel, companyId:Int,groupId:Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
            .clickable {
                navController.navigate(NavRoute.ProgramsInfo.route+"/${user.id}" )

            },

        elevation = 7.dp
    ) {


        Row(  modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 7.dp),
            //  horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxWidth().padding(start = 15.dp)) {
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
                            text = "Пользователь: ",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(

                            text = "${user.login}",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold

                        )
                    }

                }
            }
            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                    //    navController.navigate(NavRoute.UpdateProgram.route + "/${user.id},${userId},${companyId},${groupId}")
                    }

                )
            }
            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        viewModel.deleteUsers(users = user) {
                        //    navController.navigate(NavRoute.Programs.route + "/${userId}")
                        }
                    }

                )
            }
            /*
            Box(){
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick =
                    {
                        viewModel.updateUsers(
                            users = Users(
                                id = user.id,
                                login = user.login,
                                password = user.password,
                                isCompany=user.isCompany,
                                isAdmin = 1,
                                groupId = 0
                            )
                        ) {

                        }
                //        navController.navigate(NavRoute.Programs.route + "/${program.id}")


                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )

                ) {
                    Text(
                        text = "Добавить",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp
                    )
                }
            }*/
        }




    }
}