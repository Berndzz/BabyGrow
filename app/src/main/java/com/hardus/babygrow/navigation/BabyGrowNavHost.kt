package com.hardus.babygrow.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hardus.babygrow.auth.presentation.sign_in.GoogleAuthUiClient
import com.hardus.babygrow.navigation.navgraph.appNavGraph
import com.hardus.babygrow.navigation.navgraph.authNavGraph

@Composable
fun BabyGrowNavHost(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleOwner: LifecycleOwner,
    startDestination: String,
) {
    val context = LocalContext.current

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            route = ROOT_GRAPH_ROUTE
        ) {
            authNavGraph(
                navController = navController,
                googleAuthUiClient = googleAuthUiClient,
                lifecycleOwner = lifecycleOwner,
                context = context
            )
            appNavGraph(
                googleAuthUiClient = googleAuthUiClient,
                lifecycleOwner = lifecycleOwner,
                context = context
            )
        }
    }
}

