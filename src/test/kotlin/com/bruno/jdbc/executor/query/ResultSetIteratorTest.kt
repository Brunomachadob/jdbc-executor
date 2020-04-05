package com.bruno.jdbc.executor.query

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ResultSetIteratorTest {

    private val rs = mock<ResultSetRow>()
    private val underTest =
        ResultSetIterator(rs) { it.getString("name") }

    @Test
    fun `should successfully loop through the result set`() {
        whenever(rs.isLast())
            .thenReturn(false)
            .thenReturn(false)
            .thenReturn(false)
            .thenReturn(true)

        whenever(rs.getString("name"))
            .thenReturn("1")
            .thenReturn("2")
            .thenReturn("3")


        val result = underTest.asSequence().toList()

        assertEquals(listOf("1", "2", "3"), result)
    }
}
