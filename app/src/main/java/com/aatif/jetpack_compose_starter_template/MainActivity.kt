package com.aatif.jetpack_compose_starter_template

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aatif.jetpack_compose_starter_template.ui.theme.JetpackComposeStarterTemplateTheme
import com.aatif.jetpack_compose_starter_template.uix.screens.HomeScreen
import com.aatif.jetpack_compose_starter_template.uix.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeStarterTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(viewModel = homeViewModel)
                }
            }
        }
    }
}