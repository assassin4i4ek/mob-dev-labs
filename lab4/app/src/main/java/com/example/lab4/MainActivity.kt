package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.lab4.models.employee.base.Employee
import com.example.lab4.models.employee.base.props.Salary
import com.example.lab4.models.salary.SalaryCalculator
import com.example.lab4.models.salary.SalaryCalculatorImpl
import com.example.lab4.ui.salary.SalaryScreen
import com.example.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var employee: Employee? by remember {
                mutableStateOf(null)
            }
            var calculatedSalary: Salary? by remember {
                mutableStateOf(null)
            }
            val salaryCalc: SalaryCalculator = remember {
                SalaryCalculatorImpl()
            }

            Lab4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    EmployeeScreen(
//                        employee = employee,
//                        salaryCalculator = salaryCalc,
//                        onNewEmployee = {
//                            employee = it
//                        },
//                        onNewCalculatedSalary = {
//                            calculatedSalary = it
//                        }
//                    )
                    SalaryScreen(salary = Salary(500.toBigDecimal()))
                }
            }
        }
    }
}