package com.bruno.jdbc.executor.insert;

import com.bruno.jdbc.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class InsertExecutorIntegrationJavaTest extends IntegrationTest {

    @BeforeEach
    void beforeEach() {
        withConnection(conn ->
                {
                    try {
                        conn
                                .prepareStatement("CREATE TABLE users ( id INT NOT NULL, name VARCHAR(50) NOT NULL); ")
                                .execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    return null;
                }
        );
    }

    @Test
    void validatingJavaUsage() {
        withInsertExecutor(
                "INSERT INTO users(id, name) VALUES (?, ?)",
                executor -> executor
                        .execute(ps -> ps.setInt(3).setString("ThirdUser"))
                        .execute(ps -> ps.setInt(4).setString("FourthUser"))
        );

        withQueryExecutor(
                "SELECT name FROM users where id in (?, ?)",
                executor -> {
                    List<String> users = executor
                            .execute(it -> it.setInt(3).setInt(4))
                            .asStream(it -> it.getString(1))
                            .collect(Collectors.toList());

                    Assertions.assertEquals(Arrays.asList("ThirdUser", "FourthUser"), users);

                    return null;
                });
    }
}
