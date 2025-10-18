package com.herculesestacio.compatibilidade


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.herculesestacio.compatibilidade.navigation.AppNavHost
import com.herculesestacio.compatibilidade.ui.screens.QueryResultUi
import com.herculesestacio.compatibilidade.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val history = remember { mutableStateListOf<QueryResultUi>() }

        AppNavHost(
            navController = navController,
            history = history,
            onAddHistory = { item -> history.add(0, item) } // adiciona no topo
        )
    }
}
