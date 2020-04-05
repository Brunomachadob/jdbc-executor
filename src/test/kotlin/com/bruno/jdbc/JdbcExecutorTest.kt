package com.bruno.jdbc

import com.bruno.jdbc.executor.insert.InsertExecutor
import com.bruno.jdbc.executor.query.QueryExecutor
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.sql.Connection

internal class JdbcExecutorTest {

    private val connection = mock<Connection>()

    private val underTest = JdbcExecutor(connection)

    @Test
    fun `should return a valid query executor`() {
        val executor = underTest.select("SELECT")
        val (conn, sql) = executor

        Assertions.assertEquals(executor::class.java, QueryExecutor::class.java)
        Assertions.assertEquals(connection, conn)
        Assertions.assertEquals("SELECT", sql)
    }

    @Test
    fun `should return a valid insert executor`() {
        val executor = underTest.insert("INSERT")
        val (conn, sql) = executor

        Assertions.assertEquals(executor::class.java, InsertExecutor::class.java)
        Assertions.assertEquals(connection, conn)
        Assertions.assertEquals("INSERT", sql)
    }
}