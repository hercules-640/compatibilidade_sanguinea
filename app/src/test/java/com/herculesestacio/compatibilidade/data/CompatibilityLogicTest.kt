package com.herculesestacio.compatibilidade.data

import org.junit.Assert.*
import org.junit.Test

class CompatibilityLogicTest {

    // ---------- Regras gerais ----------
    @Test
    fun `O- doa para todo mundo`() {
        val recipients = listOf("O-","O+","A-","A+","B-","B+","AB-","AB+")
        recipients.forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("O-", r)
            assertTrue("O- deveria doar para $r", ok)
        }
    }

    @Test
    fun `O+ nao doa para Rh-`() {
        val rhNeg = listOf("O-","A-","B-","AB-")
        rhNeg.forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("O+", r)
            assertFalse("O+ NÃO deveria doar para $r", ok)
        }
    }

    // ---------- A ----------
    @Test
    fun `A- doa para A e AB (com qualquer Rh)`() {
        listOf("A-","A+","AB-","AB+").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("A-", r)
            assertTrue(ok)
        }
    }

    @Test
    fun `A+ soa apenas para A+ e AB+`() {
        listOf("A+", "AB+").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("A+", r)
            assertTrue(ok)
        }
        listOf("A-","AB-").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("A+", r)
            assertFalse(ok)
        }
    }

    // ---------- B ----------
    @Test
    fun `B- doa para B e AB (com qualquer Rh)`() {
        listOf("B-","B+","AB-","AB+").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("B-", r)
            assertTrue(ok)
        }
    }

    @Test
    fun `B+ so doa para B+ e AB+`() {
        listOf("B+","AB+").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("B+", r)
            assertTrue(ok)
        }
        listOf("B-","AB-").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("B+", r)
            assertFalse(ok)
        }
    }

    // ---------- AB ----------
    @Test
    fun `AB- doa apenas para AB (qualquer Rh)`() {
        listOf("AB-","AB+").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("AB-", r)
            assertTrue(ok)
        }
        listOf("O-","A-","B-","O+","A+","B+").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("AB-", r)
            assertFalse(ok)
        }
    }

    @Test
    fun `AB+ so doa para AB+`() {
        val (ok1, _) = CompatibilityLogic.isRedCellCompatible("AB+", "AB+")
        assertTrue(ok1)

        listOf("AB-","O-","O+","A-","A+","B-","B+").forEach { r ->
            val (ok, _) = CompatibilityLogic.isRedCellCompatible("AB+", r)
            assertFalse(ok)
        }
    }

    // ---------- Mensagens ----------
    @Test
    fun `mensagem de erro quando faltar algum tipo`() {
        val (ok, msg) = CompatibilityLogic.isRedCellCompatible("", "A+")
        assertFalse(ok)
        assertTrue(msg.contains("Selecione"))
    }
}
