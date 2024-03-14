package com.hardus.babygrow.main_content.home.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hardus.babygrow.util.components.CustomDetailTopAppBar
import com.hardus.babygrow.util.components.makeBold
import com.hardus.babygrow.util.components.makeMultipleBold
import com.hardus.babygrow.util.components.makeMultipleBold1
import com.hardus.babygrow.util.components.simulasiList

@Composable
fun DetailScreen(navController: NavController, simulasiId: Long) {
    val simulasi = simulasiList.find { it.simulasi.id_simulasi == simulasiId }

    if (simulasi != null) {
        Scaffold(
            topBar = {
                CustomDetailTopAppBar(
                    navController = navController,
                    title = "Edukasi " + simulasi.simulasi.judul_simulasi
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(start = 5.dp, end = 5.dp),
                ) {
                    item {
                        AsyncImage(
                            model = simulasi.simulasi.gambar_simulasi,
                            contentDescription = simulasi.simulasi.judul_simulasi,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(5.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            contentScale = ContentScale.FillWidth
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                            text = simulasi.simulasi.deskripsi_simulasi,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        navController.navigate("videoList/${simulasiId}")
                    },
                    text = {
                        Text(text = "Tonton Video Disini")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.PlayCircleOutline,
                            contentDescription = "Play Video"
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                )
            }
        )
    }
}