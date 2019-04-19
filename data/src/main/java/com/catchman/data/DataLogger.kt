package com.catchman.data


@FunctionalInterface
interface DataLogger {
    fun log(message: String)
}