package com.bruno.jdbc.executor.query

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.sql.*
import java.sql.Array

internal class ResultSetRowTest {

    private val rs = mock<ResultSet>()
    private val underTest = ResultSetRow(rs)

    @Test
    fun `getString by name`() {
        whenever(rs.getString("name")).thenReturn("value")
        assertEquals("value", underTest.getString("name"))
    }

    @Test
    fun `getString by index`() {
        whenever(rs.getString(1)).thenReturn("value")
        assertEquals("value", underTest.getString(1))
    }

    @Test
    fun `getInt by name`() {
        whenever(rs.getInt("name")).thenReturn(1)
        assertEquals(1, underTest.getInt("name"))
    }

    @Test
    fun `getInt by index`() {
        whenever(rs.getInt(1)).thenReturn(1)
        assertEquals(1, underTest.getInt(1))
    }

    @Test
    fun `getDouble by name`() {
        whenever(rs.getDouble("name")).thenReturn(1.0)
        assertEquals(1.0, underTest.getDouble("name"))
    }

    @Test
    fun `getDouble by index`() {
        whenever(rs.getDouble(1)).thenReturn(1.0)
        assertEquals(1.0, underTest.getDouble(1))
    }

    @Test
    fun `getLong by name`() {
        whenever(rs.getLong("name")).thenReturn(1)
        assertEquals(1, underTest.getLong("name"))
    }

    @Test
    fun `getLong by index`() {
        whenever(rs.getLong(1)).thenReturn(1)
        assertEquals(1, underTest.getLong(1))
    }

    @Test
    fun `getFloat by name`() {
        whenever(rs.getFloat("name")).thenReturn(1.0f)
        assertEquals(1.0f, underTest.getFloat("name"))
    }

    @Test
    fun `getFloat by index`() {
        whenever(rs.getFloat(1)).thenReturn(1.0f)
        assertEquals(1.0f, underTest.getFloat(1))
    }

    @Test
    fun `getBigDecimal by name`() {
        whenever(rs.getBigDecimal("name")).thenReturn(BigDecimal.ONE)
        assertEquals(BigDecimal.ONE, underTest.getBigDecimal("name"))
    }

    @Test
    fun `getBigDecimal by index`() {
        whenever(rs.getBigDecimal(1)).thenReturn(BigDecimal.ONE)
        assertEquals(BigDecimal.ONE, underTest.getBigDecimal(1))
    }

    @Test
    fun `getBoolean by name`() {
        whenever(rs.getBoolean("name")).thenReturn(true)
        assertEquals(true, underTest.getBoolean("name"))
    }

    @Test
    fun `getBoolean by index`() {
        whenever(rs.getBoolean(1)).thenReturn(true)
        assertEquals(true, underTest.getBoolean(1))
    }

    @Test
    fun `getTimestamp by name`() {
        val ts = Timestamp(System.currentTimeMillis())
        whenever(rs.getTimestamp("name")).thenReturn(ts)
        assertEquals(ts, underTest.getTimestamp("name"))
    }

    @Test
    fun `getTimestamp by index`() {
        val ts = Timestamp(System.currentTimeMillis())
        whenever(rs.getTimestamp(1)).thenReturn(ts)
        assertEquals(ts, underTest.getTimestamp(1))
    }

    @Test
    fun `getDate by name`() {
        val date = Date(System.currentTimeMillis())
        whenever(rs.getDate("name")).thenReturn(date)
        assertEquals(date, underTest.getDate("name"))
    }

    @Test
    fun `getDate by index`() {
        val date = Date(System.currentTimeMillis())
        whenever(rs.getDate(1)).thenReturn(date)
        assertEquals(date, underTest.getDate(1))
    }

    @Test
    fun `getArray by name`() {
        val arr = mock<Array>()
        whenever(rs.getArray("name")).thenReturn(arr)
        assertEquals(arr, underTest.getArray("name"))
    }

    @Test
    fun `getArray by index`() {
        val arr = mock<Array>()
        whenever(rs.getArray(1)).thenReturn(arr)
        assertEquals(arr, underTest.getArray(1))
    }

    @Test
    fun `getBlob by name`() {
        val blob = mock<Blob>()
        whenever(rs.getBlob("name")).thenReturn(blob)
        assertEquals(blob, underTest.getBlob("name"))
    }

    @Test
    fun `getBlob by index`() {
        val blob = mock<Blob>()
        whenever(rs.getBlob(1)).thenReturn(blob)
        assertEquals(blob, underTest.getBlob(1))
    }

    @Test
    fun `getObject by name`() {
        whenever(rs.getObject("name")).thenReturn(1)
        assertEquals(1, underTest.getObject("name"))
    }

    @Test
    fun `getObject by index`() {
        whenever(rs.getObject(1)).thenReturn(1)
        assertEquals(1, underTest.getObject(1))
    }
}
