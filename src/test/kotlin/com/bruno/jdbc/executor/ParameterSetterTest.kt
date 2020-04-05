package com.bruno.jdbc.executor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.sql.*
import java.sql.Array

internal class ParameterSetterTest {

    private val ps = mock<PreparedStatement>()
    private val underTest = ParameterSetter(ps)

    @Test
    fun `should call each method with parameter and right index`() {
        val ts = Timestamp(System.currentTimeMillis())
        val date = Date(System.currentTimeMillis())
        val arr = mock<Array>()
        val blob = mock<Blob>()

        underTest
            .setString("value")
            .setInt(10)
            .setDouble(10.0)
            .setLong(10)
            .setFloat(10f)
            .setBigDecimal(BigDecimal.ONE)
            .setBoolean(true)
            .setTimestamp(ts)
            .setDate(date)
            .setArray(arr)
            .setBlob(blob)
            .setObject(arr)

        verify(ps).setString(1, "value")
        verify(ps).setInt(2, 10)
        verify(ps).setDouble(3, 10.0)
        verify(ps).setLong(4, 10)
        verify(ps).setFloat(5, 10f)
        verify(ps).setBigDecimal(6, BigDecimal.ONE)
        verify(ps).setBoolean(7, true)
        verify(ps).setTimestamp(8, ts)
        verify(ps).setDate(9, date)
        verify(ps).setArray(10, arr)
        verify(ps).setBlob(11, blob)
        verify(ps).setObject(12, arr)
    }
}
