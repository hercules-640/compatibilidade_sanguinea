@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.herculesestacio.compatibilidade.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun HistoryScreen(history: List<QueryResultUi>, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Histórico") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        if (history.isEmpty()) {
            Column(Modifier.padding(padding).padding(16.dp)) {
                Text("Sem verificações ainda.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(Modifier.padding(padding)) {
                items(history) { item ->
                    ListItem(
                        headlineContent = {
                            Text("${item.donor} → ${item.recipient}", fontWeight = FontWeight.Bold)
                        },
                        supportingContent = { Text(item.explanation) }
                    )
                    Divider()
                }
            }
        }
    }
}
