package com.kirmc.diploma_v101.screens.moderator

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.MainViewModelFactory
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
fun mExerciseScreen(
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

    var i:Int = 0
    var programId = 0
    /*if (exerciseId != null) {
        i = exerciseId?.toInt()
    }*/

    var userId =0
    if (exerciseId != null) {
        var myIndexes = exerciseId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}

    val getId = viewModel.getProgramId(i).observeAsState().value
    if(getId!=null){
        programId = getId
    }

    var quantity by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var isActive = ""
    var setsId=""

    //var myIndexes: List<String> =
/*
    var userId: Int = 0
    var trainId: Int = 0
    var progId: Int = 0
    var exId: Int = 0
    if (exerciseId != null) {
        var myIndexes = exerciseId.split(",").toTypedArray();
        trainId = myIndexes[0]?.toInt()
        progId = myIndexes[1]?.toInt()
        exId = myIndexes[2]?.toInt()
        userId = myIndexes[3]?.toInt()

    }
*/
//    val date = getCurrentDateTime()
    //  val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
    val exercise01 = viewModel.getExercise(i).observeAsState().value
    //   val exercises1 = viewModel.showByTrainDaysId(trainId).observeAsState(listOf()).value
    val exercise1 = viewModel.showByExerciseId(i).observeAsState(listOf()).value
    //val mySets = viewModel.showSetsByDayId(trainId).observeAsState(listOf()).value
    var exercise = Exercises(
        name = Constants.Keys.NONE,
        description = Constants.Keys.NONE,
        //  programId = 0,
        trainDayId = 0
    )
    if(exercise01!=null) {
        exercise = exercise01
    }
    var set = Sets(
        quantity = "0",
        weight = "0",
        exerciseId = 0,
    )
    for (tyu in exercise1) {
        if (tyu.exerciseId == i) {
            set = tyu
            break;
        }
    }
    /*for (tyu in exercises1) {
        if (tyu.id == exId) {
            exercise = tyu
            break;
        }
    }*/

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

                                    navController.navigate(route = NavRoute.mExercises.route + "/${exercise.trainDayId},${userId}")

                                }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = "Упражнение",
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
                .padding(vertical = 9.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 9.dp, horizontal = 13.dp)
                // .clickable {
                //      navController.navigate(NavRoute.sExercise.route)


                , elevation = 7.dp
            ) {
                Column(
                    modifier = Modifier,
                    // .fillMaxSize()
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
                    .padding(vertical = 9.dp, horizontal = 13.dp)
                //  .clickable {
                //        navController.navigate(NavRoute.sExercise.route)
                ,
                elevation = 7.dp
            ) {
                Column(
                    modifier = Modifier.padding(start = 7.dp, end = 5.dp)
                    //     .fillMaxSize()


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
                    .padding(vertical = 9.dp, horizontal = 13.dp)
                //  .clickable {
                //        navController.navigate(NavRoute.sExercise.route)
                ,
                elevation = 7.dp
            ) {

            }
            LazyColumn{
                items(exercise1){
                        set -> SetsItem(
                    set = set,
                    navController = navController,
                    viewModel = mViewModel,
                    exId = i
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
    exId: Int
) {



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
        /* .clickable {
             mViewModel.initTrainDayDatabase(TYPE_EXERCISES) {
                     navController.navigate(NavRoute.TrainDay.route)
                 }
         }*/,
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

        }


    }
}