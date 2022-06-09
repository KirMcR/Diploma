package com.kirmc.diploma_v101.screens.company

import androidx.compose.runtime.Composable

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.MainViewModelFactory
import com.kirmc.diploma_v101.model.Group
import com.kirmc.diploma_v101.model.Sets
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupsScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    user1Id: String?
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val corroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    var trainId: Int = 0
    var progId: Int = 0
    var exId: Int = 0
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var isActive = ""
    var setsId=""
    var companyId =0
    //var myIndexes: List<String> =


    if (user1Id != null) {
        companyId = user1Id?.toInt()
    }

    val groups1 = viewModel.showGroupByAdminId(companyId).observeAsState(listOf()).value

    val exercises1 = viewModel.showByTrainDaysId(trainId).observeAsState(listOf()).value
    val exercise1 = viewModel.showByExerciseId(exId).observeAsState(listOf()).value
   // val mySets = viewModel.showSetsByDayId(trainId).observeAsState(listOf()).value
    var group = Group(
        name = Constants.Keys.NONE,
        companyId = companyId
    )
    var set = Sets(
        quantity = "0",
        weight = "0",
        exerciseId = 0,
    )
    for (tyu in exercise1) {
        if (tyu.exerciseId == exId) {
            set = tyu
            break;
        }
    }
   /* for (tyu in exercises1) {
        if (tyu.id == exId) {
            exercise = tyu
            break;
        }

    }
    */
    ModalBottomSheetLayout(sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = Constants.Keys.EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = {name = it},
                        label = { Text(text = "Название группы") },
                        isError = name.isEmpty()
                    )

                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.addGroup(
                                group = Group(
                                    name = name,
                                    companyId = companyId
                                )
                            ) {
                                navController.navigate(NavRoute.Groups.route + "/${companyId}")
                            }
                        }) {
                        Text(text = "Создать группу")
                    }
                }
            }
        }) {
        Scaffold(
            backgroundColor = Color.LightGray,
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
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = Color.DarkGray,
                    onClick = {
                        corroutineScope.launch {

                            bottomSheetState.show()
                        }
                        //   navController.navigate(NavRoute.AddExercises.route + "/${i}")
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Icons",
                        tint = Color.White
                    )

                }
            },

            ) {


            LazyColumn {
                items(groups1) { groups ->
                    GroupsItem(
                        groups = groups,
                        navController = navController,
                        viewModel = mViewModel,
                        companyId = companyId
                    )
                }
            }

            }

        }
    }



@Composable
fun GroupsItem(
    groups: Group,
    navController: NavHostController,
    viewModel: MainViewModel,
    companyId: Int,
    ) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)

        .clickable {
        navController.navigate(NavRoute.companyNavigation.route +  "/${groups.id}")

    },
        elevation = 7.dp
    )
    {
        Row(
            modifier = Modifier
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
                            text = "Группа: ",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(

                            text = "${groups.name}",
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

                        //  navController.navigate(NavRoute.UpdateExercise.route + "/${exercises.id}")
                    }

                )
            }
            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        viewModel.deleteGroup(group = groups) {
                            navController.navigate(NavRoute.Exercise.route + "/${groups.companyId}")
                        }
                    }

                )
            }
        }


    }
}