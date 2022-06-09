package com.kirmc.diploma_v101.screens.updateProgram

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
import com.kirmc.diploma_v101.model.Programs
import com.kirmc.diploma_v101.navigation.NavRoute
import com.kirmc.diploma_v101.utils.Constants

class updateProgram {
}
@Composable
fun UpdateProgramScreen(navController: NavController, viewModel: MainViewModel, programId:String?) {
    //val context = LocalContext.current
    val programs = viewModel.readAllPrograms().observeAsState(listOf()).value
    var program:Programs =Programs(
        name = Constants.Keys.NONE,
        description = Constants.Keys.NONE
    )
    var i:Int = 0

    var userId =0
    if (programId != null) {
        var myIndexes = programId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()}

//    if (programId != null) {
//        i = programId?.toInt()
//    }
    /*
    var userId = 0
    var companyId = 0
    var groupId = 0


    if(programId!=null){
        var myIndexes = programId.split(",").toTypedArray();
        i = myIndexes[0]?.toInt()
        userId = myIndexes[1]?.toInt()
        companyId = myIndexes[2]?.toInt()
        groupId = myIndexes[3]?.toInt()
    }*/

    for (pro in programs){
        if(pro.id == i){
            program = pro
            break
        }
    }
    var name by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var description by remember { mutableStateOf(Constants.Keys.EMPTY) }
    name = program.name
    description = program.description
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Изменение программы тренировок",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 23.sp)
                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = 9.dp
            )
        },
    )
    {
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
          //  horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Изменение программы",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 9.dp)
            )
            OutlinedTextField(
                modifier=Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text(text = "Название программы") },
              //  isError = name.isEmpty()
                )

            OutlinedTextField(
                modifier=Modifier.fillMaxWidth().weight(1f),
                value = description,
                onValueChange = {
                    description = it
                },
                label = { Text(text = "Описание программы тренировок") },
               // isError = name.isEmpty()
            )
            Button(
                modifier = Modifier.padding(15.dp),
                onClick =
                {
                    viewModel.updatePrograms(
                        programs = Programs(
                            id = program.id,
                            name = name,
                            description = description,
                            userId=program.userId,
                            isPublic = 0
                        )
                    ) {

                    }
                        navController.navigate(NavRoute.Programs.route+"/${program.userId}")
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray, contentColor = Color.White)
            ) {
                Text(text = "Обновить", fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic, fontSize =23.sp)
            }

        }

    }
}