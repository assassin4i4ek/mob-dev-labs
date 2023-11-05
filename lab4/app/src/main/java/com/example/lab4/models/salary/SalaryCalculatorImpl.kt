package com.example.lab4.models.salary

import com.example.lab4.models.employee.base.Employee
import com.example.lab4.models.employee.base.props.Salary
import com.example.lab4.models.employee.impl.Designer
import com.example.lab4.models.employee.impl.Developer
import com.example.lab4.models.utils.props.toBigDec


class SalaryCalculatorImpl: SalaryCalculator {
    override fun getSalary(employee: Employee): Salary {
        return when (employee) {
            is Developer -> getDeveloperSalary(employee)
            is Designer -> getDesignerSalary(employee)
            else -> throw IllegalArgumentException("Unknown employee type")
        }
    }

    private fun getDeveloperSalary(developer: Developer): Salary {
        return getRawEmployeeSalary(developer)
    }

    private fun getDesignerSalary(designer: Designer): Salary {
        val countedSalary = getRawEmployeeSalary(designer).value
        val efficiency = designer.efficiency.value

        return Salary(countedSalary * efficiency)
    }

    private fun getRawEmployeeSalary(employee: Employee): Salary {
        val baseSalary = employee.baseSalary.value
        val experience = employee.experience.value
        val countedSalary = when {
            experience > 5.toBigDec() -> baseSalary + 200.toBigDec()
            experience > 2.toBigDec() -> baseSalary * "1.2".toBigDec() + 200.toBigDec()
            else -> baseSalary
        }

        return Salary(countedSalary)
    }
}