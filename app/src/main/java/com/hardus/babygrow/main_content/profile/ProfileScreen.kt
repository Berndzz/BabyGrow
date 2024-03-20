package com.hardus.babygrow.main_content.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hardus.babygrow.auth.presentation.sign_in.UserData
import com.hardus.babygrow.util.components.CustomAppBar

@Composable
fun ProfileScreen(
    drawerState: DrawerState,
    userData: UserData?,
    onSignOut: () -> Unit,
    onAboutApp: () -> Unit
) {
    CardProfile(
        drawerState,
        userData,
        onSignOut,
        onAboutApp
    )
}


@Composable
fun CardProfile(
    drawerState: DrawerState,
    userData: UserData?,
    onSignOut: () -> Unit,
    onAboutApp: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = "Profile",
                userData = userData,
                isHomeScreen = false
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if (userData?.profilePictureUrl != null) {
                                AsyncImage(
                                    model = userData.profilePictureUrl,
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .size(65.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))

                            if (userData?.username != null) {
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = userData.username,
                                    textAlign = TextAlign.Center,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        ClickableText(
                            text = AnnotatedString("Detail Informasi"),
                            onClick = { /* Aksi ketika teks diklik */ },
                            style = TextStyle(
                                color = Color.Blue,
                                fontSize = 20.sp,// Warna biru
                                textDecoration = TextDecoration.Underline // Menambahkan garis bawah
                            ),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                SettingComponents(
                    iconEnd = Icons.Default.ArrowForwardIos,
                    text = "Tenting Aplikasi",
                    color = MaterialTheme.colorScheme.background,
                    onNavigate = onAboutApp
                )
                Spacer(modifier = Modifier.height(16.dp))
                SettingComponents(
                    iconEnd = Icons.Default.ArrowForwardIos,
                    text = "Sign In out",
                    color = MaterialTheme.colorScheme.background,
                    onNavigate = onSignOut
                )
            }
        }
    )
}

@Composable
fun SettingComponents(
    iconEnd: ImageVector,
    text: String,
    onNavigate: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 5.dp, end = 5.dp)
            .border(2.dp, Color.White)
            .background(
                color = color,
            )
            .clickable { onNavigate() }
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(2f),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Icon(
            imageVector = iconEnd, contentDescription = text, modifier = Modifier.weight(1f)
        )

    }
}
