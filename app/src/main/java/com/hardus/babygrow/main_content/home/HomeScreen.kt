package com.hardus.babygrow.main_content.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hardus.babygrow.auth.presentation.sign_in.UserData
import com.hardus.babygrow.util.components.CustomAppBar
import com.hardus.babygrow.util.components.SimulasiWithSubSimulasi
import com.hardus.babygrow.util.components.simulasiList

@Composable
fun HomeScreen(
    drawerState: DrawerState,
    navController: NavController,
    userData: UserData?,
) {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Scaffold(topBar = {
            CustomAppBar(
                drawerState = drawerState, title = "BABY GROW", userData, isHomeScreen = true
            )
        }, content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.primary
            ) {

                Column(Modifier.fillMaxSize()) {
                    SimulasiList(simulasiList = simulasiList) { simulasi ->
                        navController.navigate("detail/${simulasi.simulasi.id_simulasi}")
                    }
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    ) {
                        Text(text = "test")
                    }
                }
            }
        })
    }
}

@Composable
fun SimulasiList(
    simulasiList: List<SimulasiWithSubSimulasi>, onItemClick: (SimulasiWithSubSimulasi) -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        for (simulasi in simulasiList) {
            SimulasiCard(simulasi, onClick = { onItemClick(simulasi) }, needLineBreak = true)
        }
    }
}


@Composable
fun SimulasiCard(
    simulasi: SimulasiWithSubSimulasi,
    onClick: () -> Unit,
    needLineBreak: Boolean = false
) {
    val judulSimulasi = if (needLineBreak) {
        simulasi.simulasi.judul_simulasi.replaceFirst(" ", "\n")
    } else {
        simulasi.simulasi.judul_simulasi
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .clickable { onClick() }) {
            AsyncImage(
                model = simulasi.simulasi.gambar_simulasi,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .width(78.dp)
                    .height(67.dp),
                contentScale = ContentScale.FillBounds
            )
        }
        Text(text = judulSimulasi, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(4.dp))
    }
}


@Preview
@Composable
fun CheckHomeScreen() {
}