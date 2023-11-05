package com.example.lab4.models.employee.base

import com.example.lab4.models.employee.base.props.Salary
import com.example.lab4.models.employee.base.props.Experience

interface Employee {
    val baseSalary: Salary
    val experience: Experience
}