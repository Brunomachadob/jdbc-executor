package com.bruno.jdbc.executor.query

import com.bruno.jdbc.executor.ParameterSupplier
import com.bruno.jdbc.executor.SqlExecutor
import java.sql.Connection

class QueryExecutor(
    conn: Connection,
    sql: String
) : SqlExecutor<QueryResult>(conn, sql) {
    override fun execute(paramsSupplier: ParameterSupplier?): QueryResult {
        val ps = prepareStatement(paramsSupplier)

        return QueryResult(ps.executeQuery())
    }
}