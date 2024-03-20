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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hardus.babygrow.main_content.home.presentation.HomeViewModel
import com.hardus.babygrow.util.components.CustomDetailTopAppBar
import kotlin.math.roundToInt

@Composable
fun DetailScreen(
    navController: NavController,
    simulasiId: Long,
    viewModel: HomeViewModel
) {
    val simulasiList by viewModel.simulasiList.collectAsState() // Mengambil StateFlow simulasiList

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
        Scaffold(modifier = Modifier.nestedScroll(nestedScrollConnection), topBar = {
            CustomDetailTopAppBar(
                navController = navController,
                title = "Edukasi " + simulasi.simulasi.judul_simulasi
            )
        }, content = { paddingValues ->
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
                        text = formatTextWithNewLines(simulasi.simulasi.deskripsi_simulasi),
                        textAlign = TextAlign.Justify,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    simulasi.simulasi.let { simulasiDetail ->
                        DetailTextWithTitle("Manfaat:", simulasiDetail.manfaat_simulasi ?: "")
                        DetailTextWithTitle("Tips:", simulasiDetail.tips_simulasi ?: "")
                        DetailTextWithTitle("Catatan:", simulasiDetail.catatan_simulasi ?: "")
                        DetailTextWithTitle("Kebutuhan:", simulasiDetail.kebutuhan_simulasi ?: "")
                        DetailTextWithTitle("Jenis-jenis:", simulasiDetail.jenis_simulasi ?: "")
                        DetailTextWithTitle("Hal-hal Terkait:", simulasiDetail.halHal_simulasi ?: "")
                        DetailTextWithTitle("Teknik:", simulasiDetail.teknik_simulasi ?: "")
                        DetailTextWithTitle("Cara:", simulasiDetail.cara_simulasi ?: "")
                    }
                }
            }
        }, floatingActionButton = {

            ExtendedFloatingActionButton(modifier = Modifier.offset {
                IntOffset(x = 0, y = -fabOffsetHeightPx.floatValue.roundToInt())
            }, onClick = {
                navController.navigate("videoList/${simulasiId}")
            }, text = {
                Text(text = "Tonton Video Disini")
            }, icon = {
                Icon(
                    imageVector = Icons.Filled.PlayCircleOutline,
                    contentDescription = "Play Video"
                )
            }, containerColor = MaterialTheme.colorScheme.primary
            )

        })
    }
}

@Composable
fun DetailTextWithTitle(title: String, text: String) {
    if (text.isNotEmpty()) {
        Text(
            text = title,
            textAlign = TextAlign.Justify,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        val formattedText = formatTextWithNewLines(text)
        Text(
            text = formattedText,
            fontSize = 15.sp,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.padding(5.dp))
    }
}

fun formatTextWithNewLines(text: String): String {
    return text.replace("•", "\n• ")
}