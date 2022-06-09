package com.kirmc.diploma_v101.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kirmc.diploma_v101.MainViewModel
import com.kirmc.diploma_v101.screens.MainScreen
import com.kirmc.diploma_v101.screens.NavigationScreen
import com.kirmc.diploma_v101.screens.RegistrationScreen
import com.kirmc.diploma_v101.screens.addProgram.*
import com.kirmc.diploma_v101.screens.admin.addUserScreen
import com.kirmc.diploma_v101.screens.admin.adminNavigationScreen
import com.kirmc.diploma_v101.screens.admin.adminUsersScreen
import com.kirmc.diploma_v101.screens.aut.LogInScreen
import com.kirmc.diploma_v101.screens.company.*
import com.kirmc.diploma_v101.screens.moderator.mExerciseScreen
import com.kirmc.diploma_v101.screens.moderator.mExercisesScreen
import com.kirmc.diploma_v101.screens.moderator.mProgramsScreen
import com.kirmc.diploma_v101.screens.moderator.mTrainDaysScreen
import com.kirmc.diploma_v101.screens.play.PlayExerciseScreen
import com.kirmc.diploma_v101.screens.play.PlayExercisesScreen
import com.kirmc.diploma_v101.screens.shop.sExerciseScreen
import com.kirmc.diploma_v101.screens.shop.sExercisesScreen
import com.kirmc.diploma_v101.screens.shop.sProgramsScreen
import com.kirmc.diploma_v101.screens.shop.sTrainDaysScreen
import com.kirmc.diploma_v101.screens.training_info.ExercisesInfoScreen
import com.kirmc.diploma_v101.screens.training_info.ProgramsInfoScreen
import com.kirmc.diploma_v101.screens.training_info.SetsInfoScreen
import com.kirmc.diploma_v101.screens.updateProgram.UpdateExerciseScreen
import com.kirmc.diploma_v101.screens.updateProgram.UpdateProgramScreen
import com.kirmc.diploma_v101.screens.updateProgram.UpdateTrainDayScreen
import com.kirmc.diploma_v101.utils.Constants
import com.kirmc.diploma_v101.utils.Constants.Keys.ID

sealed class NavRoute(val route: String){
    object Main:NavRoute("main_screen")

    object Login: NavRoute("log_in_screen")
    object  Registration: NavRoute("registration_screen")

    object Programs: NavRoute("programs_screen")
    object  AddProgram: NavRoute("add_program_screen")

    object cPrograms: NavRoute("c_programs_screen")
    object AddCProgram: NavRoute("add_c_program_screen")
    object updateCProgram: NavRoute("update_c_program_screen")
    object cUsers: NavRoute("c_users_screen")
    object Groups: NavRoute("groups_Screen")

    //Amini
    object adminNavigation: NavRoute("admin_navigation_screen")
    object adminUsers: NavRoute("admin_users_screen")

    object UpdateProgram: NavRoute("update_program_screen")

    object TrainDays: NavRoute("train_days_screen")
    object AddTrainDay: NavRoute("add_train_day_screen")
    object UpdateTrainDay: NavRoute("update_train_day_screen")

    object Exercises: NavRoute("exercises_screen")
    object Exercise: NavRoute("exercise_screen")

    object PlayExercises: NavRoute("play_exercises_screen")
    object PlayExercise: NavRoute("play_exercise_screen")

    object AddExercises: NavRoute("add_exercise_screen")
    object UpdateExercise: NavRoute("update_exercise_screen")

    object Navigation: NavRoute("navigation_screen")

    object companyNavigation: NavRoute("company_navigation_screen")
    object addUserCompany: NavRoute("add_user_company")
    object addUser: NavRoute("add_user")

    object sPrograms: NavRoute("s_programs_screen")
    object sTrainDays: NavRoute("s_train_days_screen")
    object sExercises: NavRoute("s_exercises_screen")
    object sExercise: NavRoute("s_exercise_screen")
//Moder
    object mPrograms: NavRoute("m_programs_screen")
    object mTrainDays: NavRoute("m_train_days_screen")
    object mExercises: NavRoute("m_exercises_screen")
    object mExercise: NavRoute("m_exercise_screen")







    //Info
    object ProgramsInfo: NavRoute("programs_info_screen")
    object ExercisesInfo: NavRoute("exercises_info_screen")
    object SetsInfo: NavRoute("sets_info_screen")

    }
@Composable
fun TrainNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = NavRoute.Main.route) {
        composable(NavRoute.Main.route)
        { backStackEntry ->
            MainScreen(
                navController = navController,
                viewModel = mViewModel,
            )
        }

        composable(NavRoute.Main.route) {
            MainScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.Navigation.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            NavigationScreen(
                navController = navController,
                viewModel = mViewModel,
                userId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
        composable(NavRoute.AddProgram.route+ "/{${Constants.Keys.ID}}") {backStackEntry ->
            AddProgramScreen(
                navController = navController,
                viewModel = mViewModel,
                userId = backStackEntry.arguments?.getString(Constants.Keys.ID)

            )
        }
        composable(NavRoute.Programs.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            ProgramsScreen(
                navController = navController,
                viewModel = mViewModel,
                userId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }





        composable(NavRoute.AddCProgram.route+ "/{${Constants.Keys.ID}}") {backStackEntry ->
            AddCProgramScreen(
                navController = navController,
                viewModel = mViewModel,
                userId = backStackEntry.arguments?.getString(Constants.Keys.ID)

            )
        }
        composable(NavRoute.cPrograms.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            cProgramsScreen(
                navController = navController,
                viewModel = mViewModel,
                userId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
        composable(NavRoute.Groups.route+ "/{${Constants.Keys.ID}}") {backStackEntry ->
           GroupsScreen(
                navController = navController,
                viewModel = mViewModel,
               user1Id = backStackEntry.arguments?.getString(Constants.Keys.ID)

            )
        }
        composable(NavRoute.cUsers.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            cUsersScreen(
                navController = navController,
                viewModel = mViewModel,
                group1Id = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.adminUsers.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            adminUsersScreen(
                navController = navController,
                viewModel = mViewModel,
                adminID = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.mPrograms.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            mProgramsScreen(
                navController = navController,
                viewModel = mViewModel,
                userId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.sPrograms.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            sProgramsScreen(
                navController = navController,
                viewModel = mViewModel,
                userId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
        composable(NavRoute.UpdateProgram.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            UpdateProgramScreen(
                navController = navController,
                viewModel = mViewModel,
                programId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.updateCProgram.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            UpdateCProgramScreen(
                navController = navController,
                viewModel = mViewModel,
                programId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.AddExercises.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            AddExerciseScreen(
                navController = navController,
                viewModel = mViewModel,
                trainDayId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
        composable(NavRoute.UpdateExercise.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            UpdateExerciseScreen(
                navController = navController,
                viewModel = mViewModel,
                exerciseId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
        composable(NavRoute.Exercises.route +"/{${Constants.Keys.ID}}") {backStackEntry ->
            ExercisesScreen(
                navController = navController,
                viewModel = mViewModel,
                trainDayId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.sExercises.route +"/{${Constants.Keys.ID}}") {backStackEntry ->
            sExercisesScreen(
                navController = navController,
                viewModel = mViewModel,
                trainDayId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.mExercises.route +"/{${Constants.Keys.ID}}") {backStackEntry ->
            mExercisesScreen(
                navController = navController,
                viewModel = mViewModel,
                trainDayId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.Exercise.route +"/{${Constants.Keys.ID}}") {backStackEntry ->
            ExerciseScreen(
                navController = navController,
                viewModel = mViewModel,
                exerciseId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.sExercise.route +"/{${Constants.Keys.ID}}") {backStackEntry ->
            sExerciseScreen(
                navController = navController,
                viewModel = mViewModel,
                exerciseId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.mExercise.route +"/{${Constants.Keys.ID}}") {backStackEntry ->
            mExerciseScreen(
                navController = navController,
                viewModel = mViewModel,
                exerciseId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.AddTrainDay.route+ "/{${Constants.Keys.ID}}") {backStackEntry ->
            AddTrainDayScreen(
                navController = navController,
                viewModel = mViewModel,
                programId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.TrainDays.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            TrainDaysScreen(
                navController = navController,
                viewModel = mViewModel,
                programId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.sTrainDays.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            sTrainDaysScreen(
                navController = navController,
                viewModel = mViewModel,
                programId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.mTrainDays.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            mTrainDaysScreen(
                navController = navController,
                viewModel = mViewModel,
                programId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.UpdateTrainDay.route + "/{${Constants.Keys.ID}}") {backStackEntry ->
            UpdateTrainDayScreen(
                navController = navController,
                viewModel = mViewModel,
                trainDayId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }


        composable(NavRoute.PlayExercises.route +"/{${Constants.Keys.ID}}") {backStackEntry ->
            PlayExercisesScreen(
                navController = navController,
                viewModel = mViewModel,
                trainDayId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.PlayExercise.route +"/{${Constants.Keys.ID}}") {backStackEntry ->
            PlayExerciseScreen(
                navController = navController,
                viewModel = mViewModel,
                exerciseId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

        composable(NavRoute.companyNavigation.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            companyNavigationScreen(
                navController = navController,
                viewModel = mViewModel,
                group1Id = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
        composable(NavRoute.adminNavigation.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            adminNavigationScreen(
                navController = navController,
                viewModel = mViewModel,
                adminID = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }

                composable(NavRoute.addUserCompany.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
                    addUserCompanyScreen(
                        navController = navController,
                        viewModel = mViewModel,
                        group1Id = backStackEntry.arguments?.getString(Constants.Keys.ID)
                    )
                }
  composable(NavRoute.addUser.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
                    addUserScreen(
                        navController = navController,
                        viewModel = mViewModel,
                        adminID = backStackEntry.arguments?.getString(Constants.Keys.ID)
                    )
                }

        composable(NavRoute.Login.route) {
            LogInScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.Registration.route) {
            RegistrationScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        //Info
        composable(NavRoute.ProgramsInfo.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            ProgramsInfoScreen(
                navController = navController,
                viewModel = mViewModel,
                userId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
        composable(NavRoute.ExercisesInfo.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            ExercisesInfoScreen(
                navController = navController,
                viewModel = mViewModel,
                trainDayId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
        composable(NavRoute.SetsInfo.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            SetsInfoScreen(
                navController = navController,
                viewModel = mViewModel,
                exerciseId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
    }
}

