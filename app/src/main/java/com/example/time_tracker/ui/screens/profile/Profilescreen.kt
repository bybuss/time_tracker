package com.example.time_tracker.ui.screens.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.time_tracker.R
import com.example.time_tracker.ui.navigation.NavigationDestination
import com.example.time_tracker.ui.openTelegramLink
import com.example.time_tracker.ui.screens.BackgroungImage

object ProfileDestination: NavigationDestination {
    override val route: String = "profile"
    override val title: String = "Профиль"
}

@Composable
fun ProfileScreen() {
    Box {
        BackgroungImage()
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
        ){
                item { ProfileTopCard() }
                item { ProfileBottomCard() }
        }
    }
}


@Composable
fun ProfileTopCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 2.dp)
            .width(300.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Row(modifier = Modifier.padding(top = 8.dp)){
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(36.dp)
                    .padding(start = 16.dp, top = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(75.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Zorenko Konstantin",
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                )
                Text(
                    text = "konstantinzorenko3@gmail.com",
                    fontSize = 8.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 48.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileBottomCard() {

    val context = LocalContext.current
    val url: String = "https://t.me/stray_chat"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 2.dp, bottom = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Column(modifier = Modifier.fillMaxHeight()) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(modifier = Modifier.padding(16.dp)
                ) {
                    Column{
                        Text(
                            text = "Персональные данные",
                            color = Color.Black,
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth(),
//                                .padding(bottom = 16.dp, start = 16.dp)
                            thickness = 1.dp
                        )
                        Row {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 8.dp),
                                horizontalAlignment = Alignment.Start
                            ){
                                Text(text = "Полное имя", color = Color.DarkGray)
                                Text(text = "Zorenko Konstantin", color = Color.Black,)
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 4.dp),
                                horizontalAlignment = Alignment.Start
                            ){
                                Text(text = "Почта", color = Color.DarkGray)
                                Text(text = "konstantinzorenko3@gmail.com", color = Color.Black,)
                            }
                        }
                    }
                }
            }
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(modifier = Modifier.padding(16.dp)
                ) {
                    Column{
                        Text(
                            text = "Временной пояс",
                            color = Color.Black,
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp, start = 16.dp),
                            thickness = 1.dp
                        )
                        Text (
                            text = "👉 пример ссылки на телеграммчикс 👈",
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                openTelegramLink(context, url)
                            }
                        )
                    }
                }
            }
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(modifier = Modifier.padding(16.dp)
                ) {
                    Column{
                        Text(
                            text = "Тема приложения",
                            color = Color.Black,
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(bottom = 16.dp, start = 16.dp),
                            thickness = 1.dp
                        )
                        Text(
                            text = "Так меняют тему тока педики, котлин автоматически подтягивает тему с устройства",
                            color = Color.Black,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}