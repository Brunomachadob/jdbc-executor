package com.bruno.jdbc

import com.bruno.jdbc.executor.insert.InsertExecutor
import com.bruno.jdbc.executor.query.QueryExecutor
import org.h2.jdbcx.JdbcDataSource
import org.junit.jupiter.api.BeforeEach
import java.sql.Connection

internal abstract class IntegrationTest {

    private val dataSource = JdbcDataSource().apply {
        setURL("jdbc:h2:~/test")
        user = "sa"
        password = ""
    }

    init {
        Class.forName("org.h2.Driver")
    }

    @BeforeEach
    fun dropAllObjects() {
        withConnection {
            it.prepareStatement("DROP ALL OBJECTS").executeUpdate()
        }
    }

    fun withConnection(block: (Connection) -> Unit) {
        dataSource.connection.use(block)
    }

    protected fun withInsertExecutor(
        sql: String,
        block: (InsertExecutor) -> Unit
    ) = withConnection {
        block(InsertExecutor(it, sql))
    }

    fun withQueryExecutor(
        sql: String,
        block: (QueryExecutor) -> Unit
    ) = withConnection {
        block(QueryExecutor(it, sql))
    }
}
