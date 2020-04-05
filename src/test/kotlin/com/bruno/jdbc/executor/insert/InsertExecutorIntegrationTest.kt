package com.bruno.jdbc.executor.insert

import com.bruno.jdbc.IntegrationTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.streams.toList

internal class InsertExecutorIntegrationTest : IntegrationTest() {

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
        }
    }

    @Test
    fun `should successfully run a simple insert`() {
        withInsertExecutor("INSERT INTO users(id, name) VALUES (?, ?)") { executor ->
            executor
                .execute { it.setInt(3).setString("ThirdUser") }
                .execute { it.setInt(4).setString("FourthUser") }
        }

        withQueryExecutor("SELECT name FROM users where id in (?, ?)") { executor ->
            val users = executor.execute { it.setInt(3).setInt(4) }
                .asStream { it.getString(1) }
                .toList()

            Assertions.assertEquals(listOf("ThirdUser", "FourthUser"), users)
        }
    }
}
