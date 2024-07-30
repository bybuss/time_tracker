package com.example.time_tracker.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.time_tracker.ui.CurrentDate
import com.example.time_tracker.R
import com.example.time_tracker.ui.navigation.Screens
import com.example.time_tracker.ui.screens.BackgroungImage

@Composable
fun TasksScreen(navController: NavController) {
    Box{
        BackgroungImage ()
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = CurrentDate(),
                fontSize = 20.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.main_title),
                fontSize = 37.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = {
                          navController.navigate(route = "${Screens.TasksScreen.name}/taskMenu")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, Color(0xFF205EFF)),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.outlined_text),
                        color = Color.Black
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Button(
                onClick = {
                          navController.navigate(route = "${Screens.TasksScreen.name}/activity")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF205EFF)),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(0.7f)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(6.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.main_button_text),
                    color = Color.White
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun TasksScreenPreview() {
    val previewNavController = rememberNavController()
    TasksScreen(navController = previewNavController)
}
