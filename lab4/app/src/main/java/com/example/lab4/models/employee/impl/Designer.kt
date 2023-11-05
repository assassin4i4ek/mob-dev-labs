package com.example.lab4.models.employee.impl

import com.example.lab4.models.employee.base.props.Salary
import com.example.lab4.models.employee.base.props.Efficiency
import com.example.lab4.models.employee.base.EmployeeWithEfficiency
import com.example.lab4.models.employee.base.props.Experience

data class Designer(
    override val baseSalary: Salary,
    override val experience: Experience,
    override val efficiency: Efficiency
): EmployeeWithEfficiency
