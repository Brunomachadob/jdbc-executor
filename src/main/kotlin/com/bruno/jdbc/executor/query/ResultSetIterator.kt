package com.bruno.jdbc.executor.query

class ResultSetIterator<T>(
    private val rs: ResultSetRow,
    private val mapper: RowMapper<T>
) : Iterator<T> {
    override fun hasNext(): Boolean = !rs.isLast()

    override fun next(): T {
        rs.next()
        return mapper(rs)
    }
}
