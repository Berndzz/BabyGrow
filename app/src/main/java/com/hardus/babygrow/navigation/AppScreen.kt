package com.hardus.babygrow.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hardus.babygrow.R
import com.hardus.babygrow.auth.presentation.sign_in.GoogleAuthUiClient
import com.hardus.babygrow.main_content.home.detail.DetailScreen
import com.hardus.babygrow.main_content.home.detail.ListVideoScreen
import com.hardus.babygrow.main_content.home.laporanBayi.FormLaporanBayiScreen
import com.hardus.babygrow.main_content.home.laporanBayi.ViewModelLaporanBayi
import com.hardus.babygrow.main_content.home.presentation.HomeScreen
import com.hardus.babygrow.main_content.home.presentation.HomeViewModel
import com.hardus.babygrow.main_content.profile.ProfileScreen
import com.hardus.babygrow.main_content.profile.TentangApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    lifecycleOwner: LifecycleOwner,
    context: Context,
    googleAuthUiClient: GoogleAuthUiClient,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    val homeViewModel: HomeViewModel = viewModel()
    val viewModelLaporanBayi: ViewModelLaporanBayi = viewModel()

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            DrawerContent(
                menus = arrayOf(
                    Screens.Home,
                    Screens.Profile,
                )
            ) { route ->
                coroutineScope.launch {
                    drawerState.close()
                }
                navController.navigate(route)
            }
        }

    }) {
        NavHost(navController = navController, startDestination = Screens.Home.route) {
            composable(Screens.Home.route) {
                HomeScreen(
                    drawerState = drawerState,
                    navController = navController,
                    userData = googleAuthUiClient.getSignedInUser(),
                    onLaporanBayi = {
                        navController.navigate(route = Screens.LaporanBayi.route)
                    },
                    viewModel = viewModelLaporanBayi,
                    homeViewModel = homeViewModel
                )
            }
            composable(Screens.Profile.route) {
                ProfileScreen(drawerState = drawerState,
                    userData = googleAuthUiClient.getSignedInUser(),
                    onSignOut = {
                        lifecycleOwner.lifecycleScope.launch {
                            googleAuthUiClient.signOut()
                            Toast.makeText(
                                context, "Signed out", Toast.LENGTH_LONG
                            ).show()
                            navController.popBackStack()
                        }
                    },
                    onAboutApp = {
                        navController.navigate(route = Screens.AboutApp.route)
                    }
                )
            }
            composable(Screens.LaporanBayi.route) {
                FormLaporanBayiScreen(
                    navController = navController,
                    onHome = {
                        navController.popBackStack()
                        navController.navigate(route = Screens.Home.route)
                    },
                    viewModel = viewModelLaporanBayi,
                    userId = null
                )
            }
            composable(Screens.AboutApp.route) {
                TentangApp(navController = navController)
            }
            composable(
                route = "${Screens.LaporanBayi.route}/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.StringType })
            ) { navBackStackEntry ->
                val userId = navBackStackEntry.arguments?.getString("userId")
                FormLaporanBayiScreen(
                    navController = navController,
                    onHome = {
                        navController.popBackStack()
                        navController.navigate(route = Screens.Home.route)
                    },
                    viewModel = viewModelLaporanBayi,
                    userId = userId // Menggunakan UID yang diterima dari argumen navigasi
                )
            }
            composable("detail/{simulasiId}") { backStackEntry ->
                val simulasiId = backStackEntry.arguments?.getString("simulasiId")?.toLongOrNull()
                if (simulasiId != null) {
                    DetailScreen(
                        navController = navController,
                        simulasiId = simulasiId,
                        viewModel = homeViewModel
                    )
                } else {
                    Text(text = "Error : ID Simulasi Tidak Valid")
                }
            }

            composable("videoList/{simulasiId}") { backStackEntry ->
                val simulasiId = backStackEntry.arguments?.getString("simulasiId")?.toLongOrNull()
                if (simulasiId != null) {
                    ListVideoScreen(
                        navController = navController,
                        simulasiId = simulasiId,
                        viewModel = homeViewModel
                    )
                } else {
                    Text(text = "Error : ID Simulasi Tidak Valid")
                }
            }
        }
    }
}

@Composable
private fun DrawerContent(
    menus: Array<Screens>, onMenuClick: (String) -> Unit
) {

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val selectedColor = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentAlignment = Alignment.Center
        ) {
            Image(

                painter = painterResource(id = R.drawable.babygrow),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menus.forEachIndexed { index, screen ->
            val isSelected = selectedItemIndex == index
            NavigationDrawerItem(
                label = { screen.title?.let { Text(text = it) } },
                icon = {
                    if (isSelected && screen.selectedIcon != null) {
                        Icon(
                            imageVector = screen.selectedIcon,
                            contentDescription = null,
                            tint = selectedColor // Gunakan warna yang berbeda untuk item yang dipilih
                        )
                    } else {
                        screen.unselectedIcon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null
                            )
                        }
                    }
                },
                selected = isSelected,
                onClick = {
                    selectedItemIndex = index
                    onMenuClick(screen.route)
                },
            )
        }
    }
}


