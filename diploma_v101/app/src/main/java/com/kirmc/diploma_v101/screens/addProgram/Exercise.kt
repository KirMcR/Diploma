package com.kirmc.diploma_v101.screens.addProgram

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.MainViewModelFactory
import com.kirmc.diploma_v101.R
import com.kirmc.diploma_v101.model.Exercises
import com.kirmc.diploma_v101.model.Sets
import com.kirmc.diploma_v101.model.Training
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExerciseScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    exerciseId: String?
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val corroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    var trainDayId: Int = 0
    var progId: Int = 0
    var exId: Int = 0
    var quantity by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    var userId: Int = 0
    if (exerciseId != null) {
        var myIndexes = exerciseId.split(",").toTypedArray();
        exId = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()
    }

    var getId = viewModel.getTrainDayId(exId).observeAsState().value
    if (getId != null) {
        trainDayId = getId
    }
    getId = viewModel.getProgramId(trainDayId).observeAsState().value
    if (getId != null) {
        progId = getId
    }
    val exercise01 = viewModel.getExercise(exId).observeAsState().value
    val exercise1 = viewModel.showByExerciseId(exId).observeAsState(listOf()).value
    var exercise = Exercises(
        name = Constants.Keys.NONE,
        description = Constants.Keys.NONE,
        trainDayId = 0
    )
    if (exercise01 != null) {
        exercise = exercise01
    }
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
                        text = "Добавить подход",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = quantity,
                        onValueChange = { quantity = it },
                        label = { Text(text = "Подход") },
                        isError = quantity.isEmpty()
                    )
                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text(text = "Вес в подходе") },
                        isError = weight.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.addSets(
                                sets = Sets(

                                    quantity = quantity,
                                    weight = weight,
                                    exerciseId = exId,
                                    trainDayId = trainDayId

                                )
                            ) {
                                navController.navigate(NavRoute.Exercise.route + "/${exId},${userId}")
                            }
                        }) {
                        Text(text = "Добавить подход")
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
                                    .weight(0.25f)
                            ) {
                                Icon(imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        navController.navigate(route = NavRoute.Exercises.route + "/${trainDayId},${userId}")
                                    }
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Text(
                                    text = "Упражнение ",
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
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Icons",
                        tint = Color.White
                    )
                }
            },

            ) {

            Column(
                modifier = Modifier
                    .padding(vertical = 9.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 9.dp, horizontal = 13.dp), elevation = 7.dp
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            text = "Упражнение: ",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = exercise.name, fontSize = 27.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 9.dp, horizontal = 13.dp),
                    elevation = 7.dp
                ) {
                    Column(
                        modifier = Modifier.padding(start = 7.dp, end = 5.dp)
                    ) {
                        Text(
                            text = "Описание: ",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = exercise.description, fontSize = 23.sp)
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 9.dp, horizontal = 13.dp),
                    elevation = 7.dp
                ) {
                }
                LazyColumn {
                    items(exercise1) { set ->
                        SetsItem(
                            set = set,
                            navController = navController,
                            viewModel = mViewModel,
                            exId = exId,
                            userId = userId
                        )
                    }
                }
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick = {
                        val date = getCurrentDateTime()
                        val dateInString = date.toString("dd/MM/yyyy ss:mm:HH")
                        viewModel.addTraining(
                            training = Training(
                                dateStart = dateInString,
                                dateEnd = "",
                                isActive = 0,
                                userId = userId,
                                programId = progId,
                                trainDayId = trainDayId,
                                exerciseId = exId,
                                setId = 0,
                                isStart = 1
                            )
                        ) {}
                        navController.navigate(NavRoute.PlayExercise.route + "/${exId},${userId}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Начать",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp
                    )
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetsItem(
    set: Sets,
    navController: NavHostController,
    viewModel: MainViewModel,
    exId: Int,
    userId: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp),
        elevation = 7.dp
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            ) {
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
                            text = "Подход: ",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${set.quantity}",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Вес: ",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "${set.weight}", fontSize = 15.sp)
                    }
                }
            }
            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                    }
                )
            }
            Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        viewModel.deleteSets(sets = set) {
                            navController.navigate(NavRoute.Exercise.route + "/${exId},${userId}")
                        }
                    }
                )
            }
        }
    }
}