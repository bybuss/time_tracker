package com.example.time_tracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.time_tracker.R
import com.example.time_tracker.domain.model.TaskItem
import com.example.time_tracker.ui.navigation.TabSelection
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.time_tracker.ui.navigation.NavigationDestination
import com.example.time_tracker.ui.screens.BackgroungImage
import com.example.time_tracker.ui.screens.createTask.FormViewModel

object TasksListDestinations: NavigationDestination {
    override val route = "tasksList"
    override val title = "Список задач"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen() {
    var selectedTab by remember { mutableStateOf(TabSelection.ACTIVITY) }

    Scaffold(
        topBar ={ TopAppBar(selectedTab, onTabSelected = { selectedTab = it }) },
    ) {innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BackgroungImage()
            when (selectedTab) {
                TabSelection.ACTIVITY -> ActivityContent()
                TabSelection.TASKS -> TasksContent()
            }
        }
    }
}

@Composable
fun ActivityContent(formViewModel: FormViewModel = viewModel()) {
    val tasksState = formViewModel.tasks.collectAsState()
    val sortedTasks = remember { tasksState.value.sortedByDescending { it.isActive } }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = sortedTasks,
            key = { it.id }
        ) { task ->
            TaskCard(task = task)
        }
    }
}

@Composable
fun TasksContent(formViewModel: FormViewModel = viewModel()) { Text(text = "Тут пока ничего нет 😶‍🌫️") }

@Composable
fun TopAppBar(selectedTab: TabSelection, onTabSelected: (TabSelection) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Задачи",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Tabs(selectedTab = selectedTab, onTabSelected = onTabSelected)
    }
}

@Composable
fun Tabs(selectedTab: TabSelection, onTabSelected: (TabSelection) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(color = Color(0xFF1B232E))
            .height(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            TabButton(
                title = "АКТИВНОСТЬ",
                isSelected = selectedTab == TabSelection.ACTIVITY,
                onTabSelected = { onTabSelected(TabSelection.ACTIVITY) }
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            TabButton(
                title = "ЗАДАЧИ",
                isSelected = selectedTab == TabSelection.TASKS,
                onTabSelected = { onTabSelected(TabSelection.TASKS) }
            )
        }
    }
}

@Composable
fun TabButton(title: String, isSelected: Boolean, onTabSelected: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color(0xFF00D2E3) else Color(0xFF1B232E))
            .clickable(onClick = onTabSelected)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = if (isSelected) Color.White else Color(0xFF8076B0),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TaskCard(task: TaskItem) {
    val gradientColors = listOf(Color(0xFF00D2E3), Color(0xFF670EFD))
    var isActive by remember { mutableStateOf(task.isActive) }
    val backgroundModifier = if (isActive) {
        Modifier.background(Brush.horizontalGradient(gradientColors))
    } else {
        Modifier.background(Color(0xFF1B232E))
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),

        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.then(backgroundModifier),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { isActive = !isActive },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = if (isActive) painterResource(id = R.drawable.stop_circle) else painterResource(id = R.drawable.play_circle),
                            contentDescription = "Toggle State",
                            tint = if (isActive) Color(0xFF0B1017) else Color(0xFF8076B0)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DateAndTime(date = task.startDate, time = task.startTime)
                    DateAndTime(date = task.endDate, time = task.endTime)
                }
            }
        }
    }
}

@Composable
fun DateAndTime(date: String, time: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Filled.DateRange,
            contentDescription = "Date",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$date - $time",
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TasksListScreenPreview() {
    TasksListScreen()
}
