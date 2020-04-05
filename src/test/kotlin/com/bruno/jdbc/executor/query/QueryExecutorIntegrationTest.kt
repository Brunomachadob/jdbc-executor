package com.bruno.jdbc.executor.query

import com.bruno.jdbc.IntegrationTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.streams.toList

internal class QueryExecutorIntegrationTest : IntegrationTest() {

    internal class User(
        val name: String
    )

    internal class UserMapper : RowMapper<User> {
        override fun invoke(rs: ResultSetRow): User =
            User(
                name = rs.getString("name")
            )
    }

    @BeforeEach
    fun beforeEach() {
        withConnection {
            it.prepareStatement(
                """
                    CREATE TABLE users (
                       id INT NOT NULL,
                       name VARCHAR(50) NOT NULL
                    );
                """.trimIndent()
            ).execute()

            it.prepareStatement("INSERT INTO users(id, name) VALUES (1, 'FirstUser')").executeUpdate()
            it.prepareStatement("INSERT INTO users(id, name) VALUES (2, 'SecondUser')").executeUpdate()
        }
    }

    @Test
    fun `should successfully run a simple query without where`() {

        withQueryExecutor("SELECT name FROM users") { executor ->
            val names = executor.execute()
                .asStream { it.getString(1) }
                .limit(2)
                .toList()

            Assertions.assertEquals("FirstUser", names[0])
            Assertions.assertEquals("SecondUser", names[1])
        }
    }

    @Test
    fun `should successfully run a simple query with param`() {

        withQueryExecutor("SELECT name FROM users where name = ?") { executor ->
            val user = executor
                .execute { it.setString("FirstUser") }
                .findOne(UserMapper())

            Assertions.assertEquals("FirstUser", user.get().name)
        }
    }
}
