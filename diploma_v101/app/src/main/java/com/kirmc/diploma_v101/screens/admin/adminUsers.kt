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
import com.kirmc.diploma_v101.model.Users
import com.kirmc.diploma_v101.navigation.NavRoute
//import com.kirmc.diploma_v101.utils.DB_TYPE

@Composable
fun adminUsersScreen(navController: NavHostController, viewModel: MainViewModel, adminID:String?) {
    val context = LocalContext.current
    var companyId:Int = 0
    var groupId =0
    var adminId:Int = 0
    var whatU = 0
    var bar ="Пользователи"

    if(adminID!=null){
        var myIndexes = adminID.split(",").toTypedArray();
        adminId = myIndexes[0]?.toInt()
        whatU = myIndexes[1]?.toInt()
    }


//    if (adminID != null) {
//        groupId = group1Id?.toInt()
//    }
    val getId = viewModel.getCompanyId(groupId).observeAsState().value
    if (getId != null) {
        companyId = getId?.toInt()
    }
    /*
    if(adminID!=null){
        var myIndexes = adminID.split(",").toTypedArray();
        adminId = myIndexes[0]?.toInt()
        whatU = myIndexes[1]?.toInt()
    }*/
    var users = viewModel.readAllUsers().observeAsState(listOf()).value
    if (whatU == 0) {
        users = viewModel.getAdmins().observeAsState(listOf()).value
        bar ="Администраторы"
    } else if (whatU == 1) {
        users = viewModel.getUsualUsers().observeAsState(listOf()).value
        bar ="Пользователи"
    } else if (whatU == 2) {
        users = viewModel.getCompanies().observeAsState(listOf()).value
        bar ="Организации"
    } else if (whatU == 3) {
        users = viewModel.getModers().observeAsState(listOf()).value
        bar ="Модераторы"
    }

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
                                    navController.navigate(route = NavRoute.adminNavigation.route + "/${adminId}")
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

                        Box(modifier = Modifier
                            .weight(1f)){
                            Text(text = "${bar}", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =21.sp)
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
                    navController.navigate(NavRoute.addUser.route+"/${adminId},${whatU}" )
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
                    adminId = adminId,
                    whatU = whatU
                )

            }
        }
    }
}

@Composable
fun UsersItem(user: Users, navController: NavHostController, viewModel: MainViewModel, adminId:Int,whatU:Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
            .clickable {
                //navController.navigate(NavRoute.ProgramsInfo.route + "/${user.id}")

            },

        elevation = 7.dp
    ) {


        Row(  modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 7.dp),
            //  horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = 15.dp)) {
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
                            navController.navigate(NavRoute.adminUsers.route + "/${adminId},${whatU}")
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