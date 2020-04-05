package com.bruno.jdbc.executor.insert

import com.bruno.jdbc.executor.ParameterSupplier
import com.bruno.jdbc.executor.SqlExecutor
import java.sql.Connection

class InsertExecutor(
    conn: Connection,
    sql: String
) : SqlExecutor<InsertExecutor>(conn, sql) {
    override fun execute(paramsSupplier: ParameterSupplier?) = apply {
        prepareStatement(paramsSupplier).executeUpdate()
    }
}