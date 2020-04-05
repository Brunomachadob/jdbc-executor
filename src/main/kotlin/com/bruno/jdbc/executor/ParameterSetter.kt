package com.bruno.jdbc.executor

import java.math.BigDecimal
import java.sql.*
import java.sql.Array

class ParameterSetter(
    private val ps: PreparedStatement,
    private var index: Int = 1
) {
    fun setString(value: String) = apply { ps.setString(index++, value) }
    fun setInt(value: Int) = apply { ps.setInt(index++, value) }
    fun setDouble(value: Double) = apply { ps.setDouble(index++, value) }
    fun setLong(value: Long) = apply { ps.setLong(index++, value) }
    fun setFloat(value: Float) = apply { ps.setFloat(index++, value) }
    fun setBigDecimal(value: BigDecimal) = apply { ps.setBigDecimal(index++, value) }
    fun setBoolean(value: Boolean) = apply { ps.setBoolean(index++, value) }
    fun setTimestamp(value: Timestamp) = apply { ps.setTimestamp(index++, value) }
    fun setDate(value: Date) = apply { ps.setDate(index++, value) }
    fun setArray(value: Array) = apply { ps.setArray(index++, value) }
    fun setBlob(value: Blob) = apply { ps.setBlob(index++, value) }
    fun setObject(value: Any) = apply { ps.setObject(index++, value) }
}

