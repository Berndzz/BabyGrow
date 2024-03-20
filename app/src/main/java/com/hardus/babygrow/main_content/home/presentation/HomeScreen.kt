package com.hardus.babygrow.main_content.home.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hardus.babygrow.auth.presentation.sign_in.UserData
import com.hardus.babygrow.main_content.home.laporanBayi.ViewModelLaporanBayi
import com.hardus.babygrow.navigation.Screens
import com.hardus.babygrow.util.components.CustomAppBar
import com.hardus.babygrow.util.data.SimulasiWithSubSimulasi
import com.hardus.babygrow.util.objects.FirestoreAuth
import com.hardus.babygrow.util.objects.formatToStrinAg
import com.hardus.babygrow.util.objects.toDateA

@Composable
fun HomeScreen(
    drawerState: DrawerState,
    navController: NavController,
    userData: UserData?,
    onLaporanBayi: () -> Unit,
    viewModel: ViewModelLaporanBayi,
    homeViewModel: HomeViewModel
) {
    val personalData by viewModel.personalData
    val simulasiList by homeViewModel.simulasiList.collectAsState()
    val contentList by homeViewModel.contentList.collectAsState()
    val uid = FirestoreAuth.db.currentUser?.uid

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Scaffold(topBar = {
            CustomAppBar(
                drawerState = drawerState, title = "BABY GROW", userData, isHomeScreen = true
            )
        }) { paddingValues ->
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
                        LazyColumn(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                        ) {
                            item {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp),
                                        text = "Laporan Berat Badan Bayi",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    ElevatedCard(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 5.dp)
                                            .height(200.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.surface,
                                        ),
                                        elevation = CardDefaults.cardElevation(
                                            defaultElevation = 6.dp
                                        ),
                                    ) {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (personalData != null) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.Center
                                                ) {
                                                    Column(
                                                        modifier = Modifier
                                                            .padding(start = 20.dp)
                                                            .weight(1f),
                                                        verticalArrangement = Arrangement.Center,
                                                    ) {

                                                        Row {
                                                            Text(text = "Nama Bayi: ")
                                                            personalData?.let {
                                                                Text(
                                                                    text = it.nama,
                                                                    fontWeight = FontWeight.Bold
                                                                )
                                                            }
                                                        }
                                                        Row {
                                                            Text(text = "Berat Badan: ")
                                                            personalData?.let {
                                                                Text(
                                                                    text = it.beratBadan + " KG",
                                                                    fontWeight = FontWeight.Bold
                                                                )
                                                            }
                                                        }
                                                        Row {
                                                            Text(text = "Tanggal Lahir: ")
                                                            Text(
                                                                text = "${
                                                                    personalData?.tanggalLahir?.toDateA()
                                                                        ?.formatToStrinAg()
                                                                }", fontWeight = FontWeight.Bold
                                                            )
                                                        }
                                                        Text(text = "Catatan Tambahan: ")
                                                        personalData?.let {
                                                            it.catatanTambahan?.let { it1 ->
                                                                Text(
                                                                    text = it1,
                                                                    fontWeight = FontWeight.Bold,
                                                                )
                                                            }
                                                        }
                                                    }
                                                    Column(
                                                        modifier = Modifier
                                                            .padding(horizontal = 8.dp),
                                                        verticalArrangement = Arrangement.Center,
                                                        horizontalAlignment = Alignment.CenterHorizontally
                                                    ) {
                                                        Button(
                                                            onClick = { navController.navigate(route = "${Screens.LaporanBayi.route}/$uid")},
                                                            modifier = Modifier
                                                                .padding(top = 8.dp)
                                                                .wrapContentWidth()
                                                        ) {
                                                            Text(text = "Edit")
                                                        }
                                                    }
                                                }
                                            } else {
                                                Text(
                                                    text = "Data Masih Kosong",
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(80.dp)
                                                )
                                            }
                                        }
                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Text(
                                            text = "Silahkan isi informasi bayi di sini yah",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 14.sp
                                        )
                                        Spacer(modifier = Modifier.padding(5.dp))
                                        FilledTonalButton(onClick = { onLaporanBayi() }) {
                                            Icon(
                                                imageVector = Icons.Filled.Add,
                                                contentDescription = "Tambah Data"
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.padding(top = 10.dp))
                                Text(
                                    text = "Video Edukasi: ",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Log.d("content list", contentList.size.toString())
                                Log.d("simulasi list", simulasiList.size.toString())
                            }
                            item {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    // Kolom 1
                                    Column {
                                        contentList.take(contentList.size / 2)
                                            .forEach { video ->
                                                GridItemContent(video = video)
                                            }
                                    }

                                    Column {
                                        contentList.takeLast(contentList.size / 2)
                                            .forEach { video ->
                                                GridItemContent(video = video)
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
    simulasi: SimulasiWithSubSimulasi, onClick: () -> Unit, needLineBreak: Boolean = false
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