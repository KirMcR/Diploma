package com.kirmc.diploma_v101.screens.play

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.kirmc.diploma_v101.screens.addProgram.toString
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

@Composable
fun PlayExerciseScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    exerciseId: String?
) {

    val corroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    var exId: Int = 0
    var dateStart = ""
    var dateEnt: String =""
 //   var isPlay = 0
    var quantity by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    //var myIndexes: List<String> =

    var userId =0
    if (exerciseId != null) {
        var myIndexes = exerciseId.split(",").toTypedArray();
        exId = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}
       // dateStart = myIndexes[2]

   /* if (exerciseId != null) {
        exId = exerciseId?.toInt()
    }*/
/*
    var trainId: Int = 0
    var progId: Int = 0
    var companyId = 0
    var groupId = 0
    var userId: Int = 0

    if (exerciseId != null) {
        var myIndexes = exerciseId.split(",").toTypedArray();
        trainId = myIndexes[0]?.toInt()
        progId = myIndexes[1]?.toInt()
        exId = myIndexes[2]?.toInt()
        userId = myIndexes[3]?.toInt()
        companyId = myIndexes[4]?.toInt()
        groupId = myIndexes[5]?.toInt()
      //  dateEnt = myIndexes[4]
       // isPlay = myIndexes[4]?.toInt()
    }
*/
    var trainDayId =0
    var programId = 0
    //var userId = 0
    var groupId = 0
    var companyId = 0

    var getId = viewModel.getTrainDayId(exId).observeAsState().value
    if (getId !=null)
    {
        trainDayId = getId
    }

    getId = viewModel.getProgramId(trainDayId).observeAsState().value
    if (getId !=null)
    {
        programId = getId
    }

    getId = viewModel.getUserId(programId).observeAsState().value

   /* if (getId !=null)
    {
        userId = getId
    }*/

    getId = viewModel.getUserGroupId(userId).observeAsState().value
    if (getId !=null)
    {
        groupId = getId
    }

    getId = viewModel.getUserCompanyId(programId).observeAsState().value

    if (getId !=null)
    {
        companyId = getId
    }

    val date = getCurrentDateTime()
    val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")

  //  val training = viewModel.showByTrainingId(isPlay).observeAsState(listOf()).value
    var train = Training(
        dateStart = dateStart,
        dateEnd = "",
        isActive = 0,
        userId = 0,
        programId = 0,
        trainDayId = 0,
        exerciseId = 0,
        setId = 0,
    //    isPlay = 1
    )
   /* for (tyu in training) {
        if (tyu.isPlay == 1) {
            train = tyu
            break;
        }
    }*/

    val allTrai =viewModel.readAllTraining().observeAsState(listOf()).value
    for(check in allTrai){
        if(check.isStart!=0){
            dateStart = check.dateStart
        }
    }
    val allSets =viewModel.showSetsByDayId(trainDayId).observeAsState(listOf()).value
   // val exercises1 = viewModel.showByTrainDaysId(trainId).observeAsState(listOf()).value
    val exercise1 = viewModel.showByExerciseId(exId).observeAsState(listOf()).value


    val exerc = viewModel.getExercise(exId).observeAsState().value
    var exercise = Exercises(
        name = Constants.Keys.NONE,
        description = Constants.Keys.NONE,
     //   programId = 0,
        trainDayId = 0
    )
    if(exerc !=null){
        exercise = exerc
    }
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

                                        navController.navigate(route = NavRoute.PlayExercises.route + "/${trainDayId},${userId}")

                                    }
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Text(
                                    text = "Выполнение Упражнения",
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
                        train3 = train,
                        navController = navController,
                        viewModel = mViewModel,
                        exId = exId,
                        trainId = trainDayId,
                        progId = programId,
                        userId=userId,
                        companyId = companyId,
                        groupId = groupId,
                        dateStart = dateStart
                      //  setsActive = setsActive,
                    //    myActive = myActive,
                      //  mySets = mySets
                    )
                    }
                }
                Button(
                    modifier = Modifier.padding(15.dp),
                    onClick = {
                        val date = com.kirmc.diploma_v101.screens.addProgram.getCurrentDateTime()
//                        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
                        val dateInString = date.toString("dd/MM/yyyy ss:mm:HH")
//                        for(i in allSets){
//                            for(j in allTrai){
//                                if(i.id==j.setId)
//                                {
//
//                                    viewModel.updateTraining(
//                                        training = Training(
//                                            id =j.id,
//                                            dateStart = j.dateStart,
//                                            dateEnd = j.dateEnd,
//                                            isActive = 0,
//                                            userId = j.userId,
//                                            programId = j.programId,
//                                            trainDayId = j.trainDayId,
//                                            exerciseId = j.exerciseId,
//                                            setId = j.setId,
//                                            isStart = 1
//                                            //     isPlay = 1
//
//                                        )
//                                    )
//                                    {}
//                                }
//                            }
//                     }
                        for(i in allSets){
                        viewModel.updateSets(
                            sets = Sets(
                                id = i.id,
                                quantity = i.quantity,
                                weight = i.weight,
                                exerciseId = i.exerciseId,
                                trainDayId = i.trainDayId,
                                isActive = 0
                            )

                        ){}
                        }
                        viewModel.addTraining(
                            training = Training(
                                dateStart = dateStart,
                                dateEnd = dateInString,
                                isActive = 0,
                                userId = userId,
                                programId = programId,
                                trainDayId = trainDayId,
                                exerciseId = exId,
                                setId = 0,
                                isStart = 0
                                //     isPlay = 1

                            )
                        )
                        {}
                        navController.navigate(NavRoute.Exercise.route + "/${exId},${userId}")
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White)) {
                    Text(text = "Стоп", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
                }
            }

        }
    }



@Composable
fun SetsItem(
    set: Sets,
    train3: Training,
    navController: NavHostController,
    viewModel: MainViewModel,
    exId: Int,
    trainId: Int,
    progId: Int,
    userId: Int,
    companyId: Int,
    groupId: Int,
    dateStart: String
    // setsActive:Map<String,String>,
    // myActive:Array<String>,
   // mySets:Array<String>

) {
//    var train = train3
//    val train1 = viewModel.showByTrainingId(set.id).observeAsState(listOf()).value
//    for(i in train1.size-1 downTo 0 ){
//        if(train1[i].setId.toInt()==set.id)
//        {
//            train = train1[i]
//        }
//    }
    var colorType by remember{ mutableStateOf("-")}
   // colorType = setsActive.getValue("${set.id}")
    var myActiveId = 0
    var mySetsId = 0
    var newActive =""
    var newSets = ""
    //for(i in 0..mySets.size-1){
   //     if(myActive[i].toInt() == set.id){
      //      mySetsId = i
      //  }
   // }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 13.dp)
            .clickable {
                val date = com.kirmc.diploma_v101.screens.addProgram.getCurrentDateTime()
//                        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
                val dateInString = date.toString("dd/MM/yyyy ss:mm:HH")
                if (set.isActive == 0) {
                    viewModel.addTraining(
                        training = Training(
                            // id = train.id,
                            dateStart = dateStart,
                            dateEnd = dateInString,
                            isActive = 1,
                            userId = userId,
                            programId = progId,
                            trainDayId = set.trainDayId,
                            exerciseId = set.exerciseId,
                            setId = set.id,
                            isStart = 0
                            //           isPlay = 1
                        )
                    ) {

                    }
                    viewModel.updateSets(
                        sets = Sets(
                            id = set.id,
                            quantity = set.quantity,
                            weight = set.weight,
                            exerciseId = set.exerciseId,
                            trainDayId = set.trainDayId,
                            isActive = 1
                        )

                    ){}
                    navController.navigate(NavRoute.PlayExercise.route + "/${exId},${userId}")
                }
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
            if (set.isActive!=0) {
                Box(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                    Icon(imageVector = Icons.Default.Check,
                        contentDescription = "",
                        modifier = Modifier.clickable {

                            //  navController.navigate(NavRoute.UpdateExercise.route + "/${exercises.id}")
                        }

                    )
                }
            }
        }


    }
}