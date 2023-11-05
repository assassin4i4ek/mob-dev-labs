package com.example.lab4.models.employee.base.props

import com.example.lab4.models.utils.props.BigDecimalValidator
import java.math.BigDecimal

data class Experience(val value: BigDecimal) {
    init {
        BigDecimalValidator.validatePositive(value)
    }
}
