package com.example.arbuztest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.arbuztest.navigation.MainNavGraph
import com.example.arbuztest.ui.theme.ArbuzTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArbuzTestTheme {
                MainNavGraph()
            }
        }
    }
}
