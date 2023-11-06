package com.example.lab4.models.utils.props.validatable

import java.lang.IllegalArgumentException

interface Validatable<T> {
    fun validate(value: T)
    fun isValid(value: T): Boolean {
        return try {
            validate(value)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}