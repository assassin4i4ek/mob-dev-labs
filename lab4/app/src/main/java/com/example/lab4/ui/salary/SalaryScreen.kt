package com.example.lab4.ui.salary

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.lab4.R
import com.example.lab4.models.employee.base.props.Salary
import com.example.lab4.models.employee.base.props.SalaryCategory
import com.example.lab4.models.utils.props.toBigDecimal
import com.example.lab4.ui.theme.Lab4Theme
import com.example.lab4.ui.utils.TranslatePreview
import com.example.lab4.ui.utils.clickableNoRipple


@Composable
fun SalaryScreen(salary: Salary, onReturnClick: () -> Unit) {
    val paddingTop = dimensionResource(id = R.dimen.salary_screen_padding_top)
    val paddingHorizontal = dimensionResource(id = R.dimen.salary_screen_padding_horizontal)
    val paddingBottom = dimensionResource(id = R.dimen.salary_screen_padding_bottom)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(
            start = paddingHorizontal, top = paddingTop,
            end = paddingHorizontal, bottom = paddingBottom
        )
    ) {
        SalaryDisplay(salary)
        ReturnButton(onClick = onReturnClick)
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SalaryDisplay(salary: Salary, modifier: Modifier = Modifier) {
    var isSalaryHidden by remember(salary) {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.salary_screen_salary_display_height))
            .padding()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = isSalaryHidden,
            transitionSpec = {
                val enter = scaleIn(
                    animationSpec = tween(delayMillis = AnimationConstants.DefaultDurationMillis)
                )
                val exit = scaleOut()
                enter togetherWith exit
            },
            label = "salary"
        ) {targetIsSalaryHidden ->
            if (targetIsSalaryHidden) {
                HiddenSalaryItem(
                    salary = salary,
                    onClick = {
                        isSalaryHidden = false
                    }
                )
            }
            else {
                VisibleSalaryItem(
                    salary = salary,
                    onClick = {
                        isSalaryHidden = true
                    }
                )
            }
        }
    }
}


@Composable
fun HiddenSalaryItem(
    salary: Salary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val salaryCategory = when(SalaryCategory.forSalary(salary)) {
        SalaryCategory.JUNIOR -> stringResource(R.string.salary_screen_salary_category_junior_label)
        SalaryCategory.MIDDLE -> stringResource(R.string.salary_screen_salary_category_middle_label)
        SalaryCategory.SENIOR -> stringResource(R.string.salary_screen_salary_category_senior_label)
        SalaryCategory.OLIGARCH -> stringResource(
            id = R.string.salary_screen_salary_category_oligarch_label
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = salaryCategory,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.clickableNoRipple(onClick)
        )
        Image(
            painter = painterResource(id = R.drawable.money),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickableNoRipple(onClick)
                .padding(dimensionResource(id = R.dimen.salary_screen_salary_display_image_padding))
        )
    }
}


@Composable
fun VisibleSalaryItem(
    salary: Salary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val paddingHorizontal = dimensionResource(
        id = R.dimen.salary_screen_salary_display_value_padding_horizontal
    )
    val paddingVertical = dimensionResource(
        id = R.dimen.salary_screen_salary_display_value_padding_vertical
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = salary.value.toPlainString(),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .clickableNoRipple(onClick)
                .padding(
                    horizontal = paddingHorizontal,
                    vertical = paddingVertical
                )
        )
    }
}


@Composable
fun ReturnButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onClick) {
            Text(stringResource(R.string.salary_screen_return_button_label))
        }
    }
}


@Composable
@TranslatePreview
fun SalaryScreenPreview() {
    val salary = Salary(500.toBigDecimal())

    Lab4Theme {
        Surface {
            SalaryScreen(salary = salary, onReturnClick = {})
        }
    }
}