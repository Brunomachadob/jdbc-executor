package com.bruno.jdbc.executor.query

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.sql.ResultSet
import java.util.*
import kotlin.streams.toList

internal class QueryResultTest {

    private val rs = mock<ResultSet>()
    private val underTest = QueryResult(rs)

    @Test
    fun asResultSet() {
        assertEquals(rs, underTest.asResultSet())
    }

    @Test
    fun asIterator() {
        whenever(rs.getString("name")).thenReturn("value")

        val iterator = underTest.asIterator { it.getString("name") }

        assertEquals("value", iterator.next())
    }

    @Test
    fun asStream() {
        whenever(rs.isLast).thenReturn(false).thenReturn(true)
        whenever(rs.getString("name")).thenReturn("value")

        val stream = underTest.asStream { it.getString("name") }

        assertEquals(listOf("value"), stream.toList())
    }

    @Test
    fun findOne() {
        whenever(rs.isLast).thenReturn(false).thenReturn(true)
        whenever(rs.getString("name")).thenReturn("value")

        val result = underTest.findOne { it.getString("name") }

        assertEquals(Optional.of("value"), result)
    }
}
