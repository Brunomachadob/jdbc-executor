package com.bruno.jdbc.executor.query

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class QueryExecutorTest {
    private val connection = mock<Connection>()
    private val preparedStatement = mock<PreparedStatement>()
    private val resultSet = mock<ResultSet>()

    private val underTest = QueryExecutor(connection, "SELECT")

    @BeforeEach
    fun beforeEach() {
        whenever(connection.prepareStatement(any())).thenReturn(preparedStatement)
        whenever(preparedStatement.executeQuery()).thenReturn(resultSet)
    }

    @Test
    fun `should run a query without where`() {
        underTest.execute()

        verify(connection).prepareStatement("SELECT")
        verify(preparedStatement).executeQuery()
        verifyNoMoreInteractions(preparedStatement)
        verifyNoMoreInteractions(connection)
    }

    @Test
    fun `should run a query with where`() {
        underTest.execute { it.setString("value") }

        verify(connection).prepareStatement("SELECT")
        verify(preparedStatement).executeQuery()
        verify(preparedStatement).setString(1, "value")
        verifyNoMoreInteractions(preparedStatement)
        verifyNoMoreInteractions(connection)
    }
}