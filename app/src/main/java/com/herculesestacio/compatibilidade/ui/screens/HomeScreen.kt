@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.herculesestacio.compatibilidade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.herculesestacio.compatibilidade.data.CompatibilityLogic


@Composable
fun HomeScreen(
    onOpenHistory: () -> Unit,
    onOpenAbout: () -> Unit,
    onAddHistory: (QueryResultUi) -> Unit
) {
    val options = listOf("O-","O+","A-","A+","B-","B+","AB-","AB+")
    var donor by remember { mutableStateOf("O-") }
    var recipient by remember { mutableStateOf("A+") }
    var expandedDonor by remember { mutableStateOf(false) }
    var expandedRecipient by remember { mutableStateOf(false) }
    var resultText by remember { mutableStateOf<String?>(null) }
    var resultOk by remember { mutableStateOf<Boolean?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Compatibilidade Sanguínea") },
                actions = {
                    TextButton(onClick = onOpenHistory) { Text("Histórico") }
                    TextButton(onClick = onOpenAbout) { Text("Sobre") }
                }
            )
        }
    ) { p ->
        Column(
            modifier = Modifier.padding(p).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Verificar compatibilidade", style = MaterialTheme.typography.titleLarge)

            Text("Doador", style = MaterialTheme.typography.labelLarge)
            ExposedDropdownMenuBox(expanded = expandedDonor, onExpandedChange = { expandedDonor = it }) {
                TextField(
                    value = donor, onValueChange = {},
                    readOnly = true, label = { Text("Tipo sanguíneo") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDonor) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = expandedDonor, onDismissRequest = { expandedDonor = false }) {
                    options.forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = { donor = it; expandedDonor = false })
                    }
                }
            }

            Text("Receptor", style = MaterialTheme.typography.labelLarge)
            ExposedDropdownMenuBox(expanded = expandedRecipient, onExpandedChange = { expandedRecipient = it }) {
                TextField(
                    value = recipient, onValueChange = {},
                    readOnly = true, label = { Text("Tipo sanguíneo") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRecipient) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = expandedRecipient, onDismissRequest = { expandedRecipient = false }) {
                    options.forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = { recipient = it; expandedRecipient = false })
                    }
                }
            }

            Button(
                onClick = {
                    val (ok, msg) = CompatibilityLogic.isRedCellCompatible(donor, recipient)
                    resultOk = ok
                    resultText = msg
                    onAddHistory(QueryResultUi(donor, recipient, ok, msg, System.currentTimeMillis()))
                },
                shape = RoundedCornerShape(16.dp)
            ) { Text("Verificar") }

            if (resultText != null && resultOk != null) {
                val color = if (resultOk == true) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                ElevatedCard(shape = RoundedCornerShape(12.dp)) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Resultado", style = MaterialTheme.typography.titleLarge)
                        Text(resultText!!, color = color, fontWeight = FontWeight.Bold)
                        Text(
                            "Regras (hemácias): O→todos; A→A,AB; B→B,AB; AB→AB. Rh− doa p/ Rh− e Rh+; Rh+ só p/ Rh+.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
