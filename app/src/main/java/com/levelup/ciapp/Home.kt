
package com.levelup.ciapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Todo(val title: String, val priority: String)

@Composable
fun Home() {
    var todos by remember { mutableStateOf(listOf<Todo>()) }
    var title by remember { mutableStateOf("") }
    var prio by remember { mutableStateOf("Medium") }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    if (title.isNotBlank()) {
                        todos = todos + Todo(title, prio)
                        title = ""
                    }
                },
                icon = { Icon(Icons.Default.Add, null) },
                text = { Text("Add") }
            )
        }
    ) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            OutlinedTextField(title, { title = it }, label = { Text("Task title") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Priority: ")
                Spacer(Modifier.width(8.dp))
                PriorityChips(prio) { prio = it }
            }
            Spacer(Modifier.height(12.dp))
            if (todos.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("No tasks yet.") }
            } else {
                LazyColumn {
                    items(todos) { t ->
                        Card(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(t.title)
                                Text(colorFor(t.priority), t.priority)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Text(color: Color, content: String) {
    androidx.compose.material3.Text(text = content, color = color)
}

fun colorFor(priority: String): Color = when(priority) {
    "High" -> Color(0xFFE57373)
    "Low" -> Color(0xFF81C784)
    else -> Color(0xFFFFF176)
}

@Composable
fun PriorityChips(selected: String, onSelect: (String) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf("Low","Medium","High").forEach { p ->
            FilterChip(
                selected = selected == p,
                onClick = { onSelect(p) },
                label = { Text(p) }
            )
        }
    }
}
