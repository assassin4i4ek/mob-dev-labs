package com.example.lab4.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab4.models.employee.base.Employee
import com.example.lab4.models.employee.base.props.Salary
import com.example.lab4.models.salary.SalaryCalculator
import com.example.lab4.models.salary.SalaryCalculatorImpl
import com.example.lab4.models.utils.props.toBigDecimal
import com.example.lab4.ui.employee.EmployeeScreen
import com.example.lab4.ui.salary.SalaryScreen
import com.example.lab4.ui.theme.Lab4Theme


@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    var employee: Employee? by remember {
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
            NavHost(navController = navController, startDestination = "employee") {
                composable("employee") {
                    EmployeeScreen(
                        employee = employee,
                        salaryCalculator = salaryCalc,
                        onNewEmployee = { employee = it },
                        onNewCalculatedSalary = {
                            navController.navigate("salary/${it.value}")
                        }
                    )
                }
                composable("salary/{value}") { backStackEntry ->
                    val salaryValue = backStackEntry.arguments?.getString("value")!!
                    val salary = Salary(salaryValue.toBigDecimal())
                    SalaryScreen(
                        salary = salary,
                        onReturnClick = navController::navigateUp
                    )
                }
            }
        }
    }
}