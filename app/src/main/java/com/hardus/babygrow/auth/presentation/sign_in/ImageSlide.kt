package com.hardus.babygrow.auth.presentation.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hardus.babygrow.R
import com.hardus.babygrow.util.data.Slide
import kotlinx.coroutines.delay

@Composable
fun ImageScreen() {
    val image = listOf(
        Slide(R.drawable.vector1, stringResource(R.string.slide_1)),
        Slide(R.drawable.vector2, stringResource(R.string.slide_2)),
        Slide(R.drawable.vector3, stringResource(R.string.slide_3)),
    )
    ImageSliderWithIndicator(image = image)
}

@Composable
fun ImageSliderItem(imageRes: Int, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .width(206.88.dp)
                .height(243.dp)
        )
        Spacer(modifier = Modifier.height(8.dp)) // Spasi antara gambar dan teks
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Indicator(active: Boolean) {
    val color =
        if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
    val size = if (active) 10.dp else 10.dp
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .size(size)
    )
}

@Composable
fun ImageSliderWithIndicator(image: List<Slide>) {
    val currentIndex = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex.intValue = (currentIndex.intValue + 1) % image.size
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp) // Mengurangi padding vertikal di dalam kolom
        ) {
            ImageSliderItem(image[currentIndex.value].imageRes, image[currentIndex.value].text)
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp)) // Tambahkan spasi di sisi kiri

            image.forEachIndexed { index, _ ->
                Indicator(active = index == currentIndex.value)
                if (index < image.size - 1) {
                    Spacer(modifier = Modifier.width(8.dp)) // Mengurangi jarak antara indikator
                }
            }
            Spacer(modifier = Modifier.width(16.dp)) // Tambahkan spasi di sisi kanan
        }
    }
    Spacer(modifier = Modifier.padding(top = 30.dp))
}