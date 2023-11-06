package com.example.lab4.ui.employee

import com.example.lab4.models.employee.base.Employee
import com.example.lab4.models.employee.impl.Designer
import com.example.lab4.models.employee.impl.Developer

enum class EmployeeType {
    DEVELOPER, DESIGNER;

    companion object {
        fun forEmployee(employee: Employee): EmployeeType {
            return when (employee) {
                is Developer -> DEVELOPER
                is Designer -> DESIGNER
                else -> throw IllegalArgumentException("Unknown employee type")
            }
        }
    }
}