package com.hardus.babygrow.main_content.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hardus.babygrow.auth.presentation.sign_in.UserData
import com.hardus.babygrow.util.components.CustomAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    navController: NavController,
    userData: UserData?,
) {
    Scaffold(
        modifier = Modifier.padding(10.dp),
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = "BABY GROW",
                userData,
                isHomeScreen = true
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Text("Home")
            }
        }
    )
}