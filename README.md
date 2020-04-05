# jdbc-executor

![CI](https://github.com/Brunomachadob/jdbc-executor/workflows/CI/badge.svg)

Simple helper classes to boost your experience when using JDBC


## Example Kotlin
```kotlin
class User(
        val id: Int,
        val name: String
    )

class UserMapper : RowMapper<User> {
    override fun invoke(rs: ResultSetRow): User =
        User(
            id = rs.getInt("id"),
            name = rs.getString("name")
        )
}

JdbcExecutor(conn)
                .select("SELECT name FROM users where id = ?")
                .execute { it.setInt(1) }
                .asStream(UserMapper()) // or .asStream { it.getString("name") }
                .toList()
```

## Example Java

```java
class User {
        int id;
        String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

class UserMapper {
    public User toUser(ResultSetRow row) {
        return new User(row.getInt(1), row.getString(2));
    }
}

class Test {
    public static void main(String[] args) {
        new JdbcExecutor(conn).select("SELECT name FROM users where id in (?, ?)")
            .execute(it -> it.setInt(3).setInt(4))
            .asStream(new UserMapper()::toUser)
            .map(user -> user.name)
            .collect(Collectors.toList());
    }
}
```