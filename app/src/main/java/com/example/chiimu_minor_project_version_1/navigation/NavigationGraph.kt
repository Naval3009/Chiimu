package com.example.chiimu_minor_project_version_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chiimu_minor_project_version_1.MainActivity
import com.example.chiimu_minor_project_version_1.presentation.home_Screen.HomeScreen
import com.example.chiimu_minor_project_version_1.presentation.login_Screen.SignInScreen
import com.example.chiimu_minor_project_version_1.presentation.register_Screen.SignUpScreen
import com.example.lottieanimation_splashscreen.OnboardingScreen
import com.example.lottieanimation_splashscreen.SplashScreen

@Composable
fun NavigationGraph(context :MainActivity) {
     val navController=  rememberNavController()
    NavHost(navController = navController, startDestination = "Splash_Screen")
    {
        composable("Splash_Screen"){
            SplashScreen(navController=navController,context= context )//passing the context for the shared preference

        }
        composable("Onboarding_Screen"){
            OnboardingScreen(navController= navController, context=  context)

        }
        composable(route = "Sign_In_Screen") {
            SignInScreen(navController)

        }
        composable(route = "Sign_Up_Screen") {
            SignUpScreen(navController)

        }

        composable("Home_Screen",){
            HomeScreen()
        }

    }
    
}