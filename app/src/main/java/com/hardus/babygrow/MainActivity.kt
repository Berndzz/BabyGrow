package com.hardus.babygrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.hardus.babygrow.auth.presentation.sign_in.GoogleAuthUiClient
import com.hardus.babygrow.navigation.AUTH_GRAPH_ROUTE
import com.hardus.babygrow.navigation.BabyGrowNavHost
import com.hardus.babygrow.navigation.ROOT_GRAPH_ROUTE
import com.hardus.babygrow.navigation.Screens
import com.hardus.babygrow.ui.theme.BabyGrowTheme
import com.hardus.babygrow.util.data.Video

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()

        setContent {
            BabyGrowTheme {
                val navController = rememberNavController()
                BabyGrowNavHost(
                    navController,
                    googleAuthUiClient,
                    this,
                    startDestination = AUTH_GRAPH_ROUTE
                )
            }
        }
    }

}
