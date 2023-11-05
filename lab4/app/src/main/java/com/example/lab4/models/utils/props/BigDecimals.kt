package com.example.lab4.models.utils.props

import java.math.BigDecimal

object BigDecimals {
    // TODO limit cache size
    private val cache: MutableMap<String, BigDecimal> = mutableMapOf()

    fun cached(value: String): BigDecimal {
        return cache.getOrPut(value) { BigDecimal(value) }
    }
}

fun String.toBigDec(): BigDecimal {
    return BigDecimals.cached(this)
}

fun Int.toBigDec(): BigDecimal {
    return this.toString().toBigDec()
}

interface BigDecimalValidator {
    companion object {
        fun validatePositive(value: BigDecimal) {
            require(value >= 0.toBigDec())
        }

        fun validateUnit(value: BigDecimal) {
            require(value >= 0.toBigDec())
            require(value <= 1.toBigDec())
        }
    }
}
