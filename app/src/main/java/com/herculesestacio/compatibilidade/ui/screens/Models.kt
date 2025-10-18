package com.herculesestacio.compatibilidade.ui.screens

data class QueryResultUi(
    val donor: String,
    val recipient: String,
    val compatible: Boolean,
    val explanation: String,
    val timestampMs: Long
)
