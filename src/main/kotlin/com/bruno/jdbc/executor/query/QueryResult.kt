package com.bruno.jdbc.executor.query

import java.sql.ResultSet
import java.util.*
import java.util.stream.Stream
import java.util.stream.StreamSupport

typealias RowMapper<T> = (ResultSetRow) -> T

class QueryResult(
    private val rs: ResultSet
) {
    fun asResultSet(): ResultSet = rs

    fun <T> asIterator(mapper: RowMapper<T>): Iterator<T> =
        ResultSetIterator(
            ResultSetRow(rs), mapper
        )

    fun <T> asStream(mapper: RowMapper<T>): Stream<T> = asIterator(mapper)
        .let { Spliterators.spliteratorUnknownSize(it, 0) }
        .let { StreamSupport.stream(it, false) }

    fun <T> findOne(mapper: RowMapper<T>): Optional<T> = asStream(mapper).findFirst()
}
