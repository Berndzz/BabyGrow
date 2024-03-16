package com.hardus.babygrow.main_content.home.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hardus.babygrow.util.components.CustomDetailTopAppBar
import com.hardus.babygrow.util.components.simulasiList
import kotlin.math.roundToInt

@Composable
fun DetailScreen(navController: NavController, simulasiId: Long) {
    val simulasi = simulasiList.find { it.simulasi.id_simulasi == simulasiId }

    val fabHeight = 72.dp
    val fabHeightPx = with(
        LocalDensity.current
    ) {
        fabHeight.roundToPx().toFloat()
    }
    val fabOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = fabOffsetHeightPx.floatValue + delta
                fabOffsetHeightPx.floatValue = newOffset.coerceIn(-fabHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    if (simulasi != null) {
        Scaffold(
            modifier = Modifier.nestedScroll(nestedScrollConnection),
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
                        simulasi.simulasi.let { simulasiDetail ->
                            listOf(
                                simulasiDetail.manfaat_simulasi,
                                simulasiDetail.tips_simulasi,
                                simulasiDetail.catatan_simulasi,
                                simulasiDetail.kebutuhan_simulasi,
                                simulasiDetail.jenis_simulasi,
                                simulasiDetail.halHal_simulasi,
                                simulasiDetail.teknik_simulasi,
                                simulasiDetail.cara_simulasi
                            ).forEach { detail ->
                                detail?.let { detailText ->
                                    Text(
                                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                                        text = detailText,
                                        textAlign = TextAlign.Justify
                                    )
                                }
                            }
                        }
                    }
                }
            },
            floatingActionButton = {

                ExtendedFloatingActionButton(
                    modifier = Modifier.offset {
                        IntOffset(x = 0, y = -fabOffsetHeightPx.floatValue.roundToInt())
                    },
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