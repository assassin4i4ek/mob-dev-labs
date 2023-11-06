package com.example.lab4.models.utils.props

import java.math.BigDecimal

object BigDecimalCache {
    // TODO limit cache size
    private val cache: MutableMap<String, BigDecimal> = mutableMapOf()

    fun cached(value: String): BigDecimal {
        return cache.getOrPut(value) { BigDecimal(value) }
    }
}

fun String.toBigDecimal(cached: Boolean = false): BigDecimal {
    return if (cached) BigDecimalCache.cached(this) else BigDecimal(this)
}


fun Int.toBigDecimal(cached: Boolean = false): BigDecimal {
    return this.toString().toBigDecimal(cached)
}

interface BigDecimalValidator {
    companion object {
        fun validatePositive(value: BigDecimal) {
            require(value >= 0.toBigDecimal())
        }

        fun validateUnit(value: BigDecimal) {
            require(value >= 0.toBigDecimal())
            require(value <= 1.toBigDecimal())
        }
    }
}
