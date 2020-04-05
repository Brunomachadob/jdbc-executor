package com.bruno.jdbc

import com.bruno.jdbc.executor.insert.InsertExecutor
import com.bruno.jdbc.executor.query.QueryExecutor
import java.sql.Connection

class JdbcExecutor(
    private val conn: Connection
) {
    fun select(sql: String): QueryExecutor = QueryExecutor(conn, sql)
    fun insert(sql: String): InsertExecutor = InsertExecutor(conn, sql)
}
