package com.example.lab4.models.employee.base.props

import com.example.lab4.models.utils.props.BigDecimalValidator
import com.example.lab4.models.utils.props.validatable.ValidatableLambda
import java.math.BigDecimal

data class Experience(val value: BigDecimal) {
    init {
        validate(value)
    }

    companion object: ValidatableLambda<BigDecimal>(BigDecimalValidator::validatePositive)
}
