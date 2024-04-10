package com.example.chiimu_minor_project_version_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.chiimu_minor_project_version_1.navigation.NavigationGraph
import com.example.chiimu_minor_project_version_1.ui.theme.Chiimu_minor_project_version_1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chiimu_minor_project_version_1Theme {
                NavigationGraph(context = this@MainActivity)

            }
        }
    }
}

