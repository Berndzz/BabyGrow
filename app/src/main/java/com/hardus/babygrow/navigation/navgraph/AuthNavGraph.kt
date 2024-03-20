package com.hardus.babygrow.navigation.navgraph

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hardus.babygrow.auth.presentation.sign_in.GoogleAuthUiClient
import com.hardus.babygrow.auth.presentation.sign_in.SignInScreen
import com.hardus.babygrow.auth.presentation.sign_in.SignInViewModel
import com.hardus.babygrow.navigation.APP_GRAPH_ROUTE
import com.hardus.babygrow.navigation.AUTH_GRAPH_ROUTE
import com.hardus.babygrow.navigation.Screens
import kotlinx.coroutines.launch


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleOwner: LifecycleOwner,
    context: Context
) {
    navigation(
        startDestination = Screens.SignIn.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(route = Screens.SignIn.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle(lifecycleOwner)
            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(route = APP_GRAPH_ROUTE) {
                        popUpTo(AUTH_GRAPH_ROUTE) {
                            inclusive = false
                        }
                    }
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        lifecycleOwner.lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate(route = APP_GRAPH_ROUTE)
                    viewModel.resetState()
                }
            }

            SignInScreen(
                state = state,
                onSignInClick = {
                    lifecycleOwner.lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                isLoading = state.isLoading
            )
        }

        composable(route = "sign_out") { // Tambahkan tujuan baru untuk proses logout
            LaunchedEffect(key1 = Unit) {
                // Lakukan proses logout
                googleAuthUiClient.signOut()
                // Navigasi kembali ke layar sign-in setelah logout
                navController.navigate(Screens.SignIn.route) {
                    // Membersihkan back stack
                    popUpTo(AUTH_GRAPH_ROUTE) {
                        inclusive = true // Termasuk tujuan awal dalam grafik autentikasi
                    }
                }
            }
        }
    }
}
