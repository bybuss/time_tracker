package com.example.time_tracker.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.time_tracker.ui.screens.BackgroungImage
import com.example.time_tracker.ui.viewmodels.form.FormViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskMenu(
        navController: NavController,
        formViewModel: FormViewModel = viewModel(),
) {
   val formUiState = formViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BackgroungImage()
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1B232E),
                contentColor = Color(0xFFFFFFFF)
            ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Создать задачу",
                        modifier = Modifier.weight(1f),
                    )
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier.size(24.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = formUiState.value.title,
                    onValueChange = {formViewModel.updateTitle(it)},
                    label = { Text("Название... |", color = Color.LightGray) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = formUiState.value.startDate,
                        onValueChange = {formViewModel.updateStartDate(it)},
                        label = { Text("Дата", color = Color.LightGray) },
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "по", modifier = Modifier.padding(top= 16.dp), color = Color.White)
                        Text(text = "_", color = Color.White)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = formUiState.value.endDate,
                        onValueChange = {formViewModel.updateEndDate(it)},
                        label = { Text("Дата", color = Color.LightGray) },
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Ряд для выбора времени выполнения
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = formUiState.value.startTime,
                        onValueChange = {formViewModel.updateStartTime(it)},
                        label = { Text("Время выполнения", color = Color.LightGray) },
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = formUiState.value.endTime,
                        onValueChange = {formViewModel.updateEndTime(it)},
                        label = { Text("10минут", color = Color.LightGray) },
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column(modifier = Modifier.width(160.dp)) {
                    Text(
                        text = "Есть проект в Asana?",
                    )
                    Text(
                        text = "Благодаря нашему сервису вы легко можете управлять своими проектами из Asana!",
                        fontSize = 8.sp,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    OutlinedTextField(
                        value = formUiState.value.asanaUrl,
                        onValueChange = {formViewModel.updateAsanaUrl(it)},
                        label = { Text("Ключ проекта...") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .width(400.dp)
                            .weight(1f),
                    )
                    OutlinedButton(
                        onClick = { /* TODO */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.run { buttonColors(containerColor = Color(0xFF6200EE)) } // Используем Material Purple 500
                    ) {
                        Text(
                            text = "Отправить",
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { formViewModel.addTask() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(50), // Закругленные углы
                    colors = ButtonDefaults.run { buttonColors(containerColor = Color(0xFF6200EE)) } // Используем Material Purple 500
                ) {
                    Text(
                        text = "Создать проект",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskMenuPreview() {
    val previewNavController = rememberNavController()
    TaskMenu(navController = previewNavController)
}