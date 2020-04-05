package com.bruno.jdbc.executor

import java.sql.Connection
import java.sql.PreparedStatement

typealias ParameterSupplier = (ParameterSetter) -> Unit

abstract class SqlExecutor<T>(
    private val conn: Connection,
    private val sql: String
) {
    operator fun component1(): Connection = conn
    operator fun component2(): String = sql

    protected fun prepareStatement(paramsSupplier: ParameterSupplier?): PreparedStatement = conn
        .prepareStatement(sql)
        .also {
            paramsSupplier?.invoke(ParameterSetter(it))
        }

    abstract fun execute(paramsSupplier: ParameterSupplier? = null): T
}