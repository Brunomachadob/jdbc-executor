package com.bruno.jdbc.executor.query

import java.math.BigDecimal
import java.sql.*
import java.sql.Array

class ResultSetRow(
    private val rs: ResultSet
) {
    fun isLast(): Boolean = rs.isLast
    fun next(): Boolean = rs.next()

    fun getString(name: String): String = rs.getString(name)
    fun getString(index: Int): String = rs.getString(index)

    fun getInt(name: String): Int = rs.getInt(name)
    fun getInt(index: Int): Int = rs.getInt(index)

    fun getDouble(name: String): Double = rs.getDouble(name)
    fun getDouble(index: Int): Double = rs.getDouble(index)

    fun getLong(name: String): Long = rs.getLong(name)
    fun getLong(index: Int): Long = rs.getLong(index)

    fun getFloat(name: String): Float = rs.getFloat(name)
    fun getFloat(index: Int): Float = rs.getFloat(index)

    fun getBigDecimal(name: String): BigDecimal = rs.getBigDecimal(name)
    fun getBigDecimal(index: Int): BigDecimal = rs.getBigDecimal(index)

    fun getBoolean(name: String): Boolean = rs.getBoolean(name)
    fun getBoolean(index: Int): Boolean = rs.getBoolean(index)

    fun getTimestamp(name: String): Timestamp = rs.getTimestamp(name)
    fun getTimestamp(index: Int): Timestamp = rs.getTimestamp(index)

    fun getDate(name: String): Date = rs.getDate(name)
    fun getDate(index: Int): Date = rs.getDate(index)

    fun getArray(name: String): Array = rs.getArray(name)
    fun getArray(index: Int): Array = rs.getArray(index)

    fun getBlob(name: String): Blob = rs.getBlob(name)
    fun getBlob(index: Int): Blob = rs.getBlob(index)

    fun getObject(name: String): Any = rs.getObject(name)
    fun getObject(index: Int): Any = rs.getObject(index)
}
