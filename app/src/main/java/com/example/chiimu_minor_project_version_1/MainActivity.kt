package com.example.chiimu_minor_project_version_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chiimu_minor_project_version_1.navigation.NavigationGraph
import com.example.chiimu_minor_project_version_1.ui.theme.Chiimu_minor_project_version_1Theme

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

