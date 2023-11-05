package com.example.lab4.models.salary

import com.example.lab4.models.employee.base.Employee
import com.example.lab4.models.employee.base.props.Salary

interface SalaryCalculator {
    fun getSalary(employee: Employee): Salary
}