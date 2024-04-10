package com.example.chiimu_minor_project_version_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chiimu_minor_project_version_1.MainActivity
import com.example.chiimu_minor_project_version_1.presentation.home_Screen.HomeScreen
import com.example.lottieanimation_splashscreen.OnboardingScreen
import com.example.lottieanimation_splashscreen.SplashScreen

@Composable
fun NavigationGraph(context :MainActivity) {
     val navController=  rememberNavController()
    NavHost(navController = navController, startDestination = "Splash")
    {
        composable("Splash"){
            SplashScreen(navController=navController,context= context )//passing the context for the shared preference

        }
        composable("Onboarding"){
            OnboardingScreen(navController= navController, context=  context)

        }
        composable("Home",){
            HomeScreen()


        }

    }
    
}