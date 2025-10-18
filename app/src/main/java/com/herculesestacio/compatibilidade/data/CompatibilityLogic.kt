package com.herculesestacio.compatibilidade.data

/**
 * Lógica central de compatibilidade sanguínea (hemácias, ABO + Rh).
 * Retorna (compatível, explicação).
 */
object CompatibilityLogic {

    private val aboRules = mapOf(
        "O"  to listOf("O", "A", "B", "AB"),
        "A"  to listOf("A", "AB"),
        "B"  to listOf("B", "AB"),
        "AB" to listOf("AB")
    )

    fun isRedCellCompatible(donor: String, recipient: String): Pair<Boolean, String> {
        if (donor.isBlank() || recipient.isBlank()) {
            return false to "Selecione ambos os tipos sanguíneos."
        }

        val donorBase = donor.replace("+", "").replace("-", "")
        val recipientBase = recipient.replace("+", "").replace("-", "")
        val donorRhPositive = donor.contains("+")
        val recipientRhPositive = recipient.contains("+")

        val aboOk = aboRules[donorBase]?.contains(recipientBase) == true
        val rhOk = !donorRhPositive || recipientRhPositive

        val compatible = aboOk && rhOk

        val explanation = when {
            !aboOk -> "Incompatibilidade ABO: $donor doa apenas para ${aboRules[donorBase]?.joinToString(", ")}."
            !rhOk  -> "Incompatibilidade Rh: Rh+ doa apenas para Rh+."
            else   -> "Compatível! $donor → $recipient é uma doação possível."
        }

        return compatible to explanation
    }
}