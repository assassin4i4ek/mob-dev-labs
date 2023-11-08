package com.example.lab4.models.employee.base.props

import com.example.lab4.models.utils.props.toBigDecimal

enum class SalaryCategory {
    JUNIOR, MIDDLE, SENIOR, OLIGARCH;

    companion object {
        fun forSalary(salary: Salary): SalaryCategory {
            return when {
                salary.value <= 1000.toBigDecimal(cached = true) -> JUNIOR
                salary.value <= 2000.toBigDecimal(cached = true) -> MIDDLE
                salary.value <= 3000.toBigDecimal(cached = true) -> SENIOR
                else -> OLIGARCH
            }
        }
    }
}