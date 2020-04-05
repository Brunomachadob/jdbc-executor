package com.bruno.jdbc.executor.insert

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class InsertExecutorTest {
    private val connection = mock<Connection>()
    private val preparedStatement = mock<PreparedStatement>()
    private val resultSet = mock<ResultSet>()

    private val underTest = InsertExecutor(connection, "INSERT")

    @BeforeEach
    fun beforeEach() {
        whenever(connection.prepareStatement(any())).thenReturn(preparedStatement)
        whenever(preparedStatement.executeQuery()).thenReturn(resultSet)
    }

    @Test
    fun `should run a insert without where`() {
        underTest.execute()

        verify(connection).prepareStatement("INSERT")
        verify(preparedStatement).executeUpdate()
        verifyNoMoreInteractions(preparedStatement)
        verifyNoMoreInteractions(connection)
    }

    @Test
    fun `should run a insert with where`() {
        underTest.execute { it.setString("value") }

        verify(connection).prepareStatement("INSERT")
        verify(preparedStatement).executeUpdate()
        verify(preparedStatement).setString(1, "value")
        verifyNoMoreInteractions(preparedStatement)
        verifyNoMoreInteractions(connection)
    }
}