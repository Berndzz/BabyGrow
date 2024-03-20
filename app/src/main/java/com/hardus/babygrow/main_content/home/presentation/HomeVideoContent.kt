package com.hardus.babygrow.main_content.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hardus.babygrow.main_content.home.detail.YouTubePlayer
import com.hardus.babygrow.util.data.Video

@Composable
fun GridItemContent(video: Video) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .width(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YouTubePlayer(
            youtubeVideoId = video.url_video,
            lifecycleOwner = LocalLifecycleOwner.current,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = video.judul_video,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


