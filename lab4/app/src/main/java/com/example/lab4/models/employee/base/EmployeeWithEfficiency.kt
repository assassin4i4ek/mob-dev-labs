package com.example.lab4.models.employee.base

import com.example.lab4.models.employee.base.props.Efficiency

interface EmployeeWithEfficiency: Employee {
    val efficiency: Efficiency
}