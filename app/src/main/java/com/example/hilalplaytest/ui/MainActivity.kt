package com.example.hilalplaytest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.hilalplaytest.ui.theme.HilalPlayTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HilalPlayTestTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}