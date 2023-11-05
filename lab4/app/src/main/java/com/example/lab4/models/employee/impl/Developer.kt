package com.example.lab4.models.employee.impl

import com.example.lab4.models.employee.base.props.Salary
import com.example.lab4.models.employee.base.Employee
import com.example.lab4.models.employee.base.props.Experience

data class Developer(
    override val baseSalary: Salary,
    override val experience: Experience
): Employee
