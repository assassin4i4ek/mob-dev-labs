package com.example.lab4.ui.employee

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.lab4.R
import com.example.lab4.models.employee.base.Employee
import com.example.lab4.models.employee.base.EmployeeWithEfficiency
import com.example.lab4.models.employee.base.props.Efficiency
import com.example.lab4.models.employee.base.props.Experience
import com.example.lab4.models.employee.base.props.Salary
import com.example.lab4.models.employee.impl.Designer
import com.example.lab4.models.employee.impl.Developer
import com.example.lab4.models.salary.SalaryCalculator
import com.example.lab4.models.salary.SalaryCalculatorImpl
import com.example.lab4.models.utils.props.toBigDecimal
import com.example.lab4.ui.theme.Lab4Theme
import com.example.lab4.ui.utils.TranslatePreview
import java.math.BigDecimal

@Composable
fun EmployeeScreen(
    employee: Employee?,
    salaryCalculator: SalaryCalculator,
    onNewEmployee: (Employee) -> Unit,
    onNewCalculatedSalary: (Salary) -> Unit
) {
    /* Util functions */
    fun isValidBigDecimalProp(value: String, validate: (BigDecimal) -> Boolean): Boolean {
        return value.toBigDecimalOrNull()?.let(validate)?: false
    }

    /* States */
    var employeeType by rememberSaveable {
        mutableStateOf(employee?.let(EmployeeType::forEmployee) ?: EmployeeType.DEVELOPER)
    }

    var baseSalaryText by rememberSaveable {
        mutableStateOf(employee?.baseSalary?.value?.toString() ?: "")
    }
    var isBaseSalaryValid by rememberSaveable {
        mutableStateOf(true)
    }

    var experienceText by rememberSaveable {
        mutableStateOf(employee?.experience?.value?.toString() ?: "")
    }
    var isExperienceValid by rememberSaveable {
        mutableStateOf(true)
    }

    var efficiencyText by rememberSaveable {
        val eff = if (employee is EmployeeWithEfficiency) employee.efficiency else null
        mutableStateOf(eff?.value?.toString() ?: "")
    }
    var isEfficiencyValid by rememberSaveable {
        mutableStateOf(true)
    }
    val applyEfficiency by remember {
        derivedStateOf {
            employeeType in arrayOf(EmployeeType.DESIGNER)
        }
    }

    val canGetSalary by remember {
        derivedStateOf {
            isBaseSalaryValid and isExperienceValid and isEfficiencyValid and
                    baseSalaryText.isNotEmpty() and experienceText.isNotEmpty() and
                    (!applyEfficiency or efficiencyText.isNotEmpty())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = dimensionResource(id = R.dimen.employee_screen_padding_horizontal),
                vertical = dimensionResource(id = R.dimen.employee_screen_padding_vertical)
            )
    ) {
        BaseSalaryInput(
            value = baseSalaryText,
            onValueChange = {
                baseSalaryText = it
                isBaseSalaryValid = isValidBigDecimalProp(it, Salary::isValid)
            },
            isError = !isBaseSalaryValid
        )
        ExperienceInput(
            value = experienceText,
            onValueChange = {
                experienceText = it
                isExperienceValid = isValidBigDecimalProp(it, Experience::isValid)
            },
            isError = !isExperienceValid
        )
        EfficiencyInput(
            value = efficiencyText,
            onValueChange = {
                efficiencyText = it
                isEfficiencyValid = isValidBigDecimalProp(it, Efficiency::isValid)
            },
            visible = applyEfficiency,
            isError = !isEfficiencyValid
        )
        EmployeeTypeSwitch(
            type = employeeType,
            onTypeChange = {
                employeeType = it
            }
        )
        GetSalaryButton(
            onClick = {
                val newEmployee: Employee = when(employeeType) {
                    EmployeeType.DEVELOPER -> Developer(
                        baseSalary = Salary(baseSalaryText.toBigDecimal()),
                        experience = Experience(experienceText.toBigDecimal())
                    )
                    EmployeeType.DESIGNER -> Designer(
                        baseSalary = Salary(baseSalaryText.toBigDecimal()),
                        experience = Experience(experienceText.toBigDecimal()),
                        efficiency = Efficiency(efficiencyText.toBigDecimal())
                    )
                }
                val calculatedSalary = salaryCalculator.getSalary(newEmployee)
                onNewEmployee(newEmployee)
                onNewCalculatedSalary(calculatedSalary)
            },
            enabled = canGetSalary
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    isError: Boolean = false,
    label: String = "",
    errorText: String = "",
    keyboardType: KeyboardType = KeyboardType.Number
) {
    val paddingBottom = dimensionResource(
        id = R.dimen.employee_screen_text_field_padding_bottom
    )

    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = paddingBottom)
                .then(modifier),
            label = {
                Text(label)
            },
            supportingText = {
                if (isError) {
                    Text(errorText)
                }
                else {
                    Text("")
                }
            },
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            singleLine = true
        )
    }
}


@Composable
fun BaseSalaryInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    isError: Boolean = false
) {
    BaseTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        visible = visible,
        isError = isError,
        label = stringResource(R.string.employee_screen_base_salary_input_label),
        errorText = stringResource(R.string.employee_screen_base_salary_input_error_text)
    )
}


@Composable
fun ExperienceInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    isError: Boolean = false
) {
    BaseTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        visible = visible,
        isError = isError,
        label = stringResource(R.string.employee_screen_experience_input_label),
        errorText = stringResource(R.string.employee_screen_experience_input_error_text)
    )
}


@Composable
fun EfficiencyInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    isError: Boolean = false
) {
    BaseTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        visible = visible,
        isError = isError,
        label = stringResource(R.string.employee_screen_efficiency_input_label),
        errorText = stringResource(R.string.employee_screen_efficiency_input_error_text)
    )
}


@Composable
fun RadioButtonLabeled(
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(selected, onClick = onSelect)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
        )
        Text(label)
    }
}


@Composable
fun EmployeeTypeSwitch(
    type: EmployeeType,
    onTypeChange: (EmployeeType) -> Unit,
    modifier: Modifier = Modifier
) {
    val paddingBottom = dimensionResource(
        id = R.dimen.employee_screen_employee_type_switch_padding_bottom
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = paddingBottom)
        .then(modifier)) {
        RadioButtonLabeled(
            selected = type == EmployeeType.DEVELOPER,
            onSelect = { onTypeChange(EmployeeType.DEVELOPER) },
            label = stringResource(R.string.employee_screen_employee_type_developer)
        )
        RadioButtonLabeled(
            selected = type == EmployeeType.DESIGNER,
            onSelect = { onTypeChange(EmployeeType.DESIGNER) },
            label = stringResource(R.string.employee_screen_employee_type_designer)
        )
    }
}


@Composable
fun GetSalaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick,
            enabled = enabled
        ) {
            Text(stringResource(R.string.employee_screen_get_salary_button_label))
        }
    }
}


@Composable
@TranslatePreview
fun EmployeeScreenPreview() {
    val employee = Designer(
        baseSalary = Salary(150.toBigDecimal()),
        experience = Experience("4.5".toBigDecimal()),
        efficiency = Efficiency("0.8".toBigDecimal())
    )
    val calc = SalaryCalculatorImpl()

    Lab4Theme {
        Surface {
            EmployeeScreen(
                employee = employee,
                salaryCalculator = calc,
                onNewEmployee = {},
                onNewCalculatedSalary = {}
            )
        }
    }
}

