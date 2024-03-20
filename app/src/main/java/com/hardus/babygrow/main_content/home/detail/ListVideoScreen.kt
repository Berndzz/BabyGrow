package com.hardus.babygrow.main_content.home.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.hardus.babygrow.main_content.home.presentation.HomeViewModel
import com.hardus.babygrow.util.components.CustomDetailTopAppBar
import com.hardus.babygrow.util.data.SubSimulasi
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun ListVideoScreen(
    navController: NavController,
    simulasiId: Long,
    viewModel: HomeViewModel
) {
    val simulasiList by viewModel.simulasiList.collectAsState() // Mengambil StateFlow simulasiList

    val simulasi = simulasiList.find { it.simulasi.id_simulasi == simulasiId }
    if (simulasi != null) {
        Scaffold(
            topBar = {
                CustomDetailTopAppBar(
                    navController = navController,
                    title = "Daftar Video ${simulasi.simulasi.judul_simulasi}"
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(simulasi.subSimulasi) { subSimulasi ->
                        VideoListItem(subSimulasi = subSimulasi)
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                }
            }
        )
    }
}


@Composable
fun VideoListItem(subSimulasi: SubSimulasi) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            YouTubePlayer(
                youtubeVideoId = subSimulasi.url_video,
                lifecycleOwner = LocalLifecycleOwner.current,
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column {
                Text(
                    text = subSimulasi.judul_video,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subSimulasi.deskripsi_video,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner,
    modifier: Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val playerView = YouTubePlayerView(context)
            playerView.apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val defaultPlayerUiController =
                            DefaultPlayerUiController(playerView, youTubePlayer)
                        playerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                        youTubePlayer.cueVideo(youtubeVideoId, 0f)
                    }
                })
            }
        }
    )
}



