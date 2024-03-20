package com.hardus.babygrow.navigation.navgraph

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hardus.babygrow.auth.presentation.sign_in.GoogleAuthUiClient
import com.hardus.babygrow.navigation.APP_GRAPH_ROUTE
import com.hardus.babygrow.navigation.AppScreen
import com.hardus.babygrow.navigation.Screens

fun NavGraphBuilder.appNavGraph(
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleOwner: LifecycleOwner,
    context: Context
) {
    navigation(
        startDestination = Screens.Home.route,
        route = APP_GRAPH_ROUTE
    ) {
        composable(route = Screens.Home.route) {
            AppScreen(
                googleAuthUiClient = googleAuthUiClient,
                lifecycleOwner = lifecycleOwner,
                context = context
            )
        }
    }
}