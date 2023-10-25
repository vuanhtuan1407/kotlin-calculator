package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import net.objecthunter.exp4j.ExpressionBuilder
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme

fun solveMathOperation(operation: String): Double {
    return ExpressionBuilder(operation).build().evaluate()
}

class MainActivity : ComponentActivity() {
    private lateinit var input: TextView
    private lateinit var output: TextView

    private var currentNumber: String = ""
    private var currentResult: Double = 0.0

    private var isFirstEvaluation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        output = findViewById(R.id.output)

        val button0: Button = findViewById(R.id.button_0)
        val button1: Button = findViewById(R.id.button_1)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.button_3)
        val button4: Button = findViewById(R.id.button_4)
        val button5: Button = findViewById(R.id.button_5)
        val button6: Button = findViewById(R.id.button_6)
        val button7: Button = findViewById(R.id.button_7)
        val button8: Button = findViewById(R.id.button_8)
        val button9: Button = findViewById(R.id.button_9)

        val buttonClear: Button = findViewById(R.id.button_clear)
        val buttonBackSpace: Button = findViewById(R.id.button_backspace)
        val buttonCE: Button = findViewById(R.id.button_ce)
        val buttonDivision: Button = findViewById(R.id.button_division)
        val buttonMultiply: Button = findViewById(R.id.multiply)
        val buttonMinus: Button = findViewById(R.id.button_minus)
        val buttonPlus: Button = findViewById(R.id.button_plus)
        val buttonEqual: Button = findViewById(R.id.button_equal)
        val buttonComma: Button = findViewById(R.id.button_comma)

        val numberButtons = listOf(
            button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                currentNumber += button.text
                input.text = currentNumber
            }
        }

        buttonPlus.setOnClickListener { performOperation("+") }
        buttonMinus.setOnClickListener { performOperation("-") }
        buttonMultiply.setOnClickListener { performOperation("*") }
        buttonDivision.setOnClickListener { performOperation("/") }

        buttonEqual.setOnClickListener {
            try {
                currentResult = solveMathOperation(currentNumber)
                output.text = currentResult.toString()
            } catch (e: Exception) {
                output.text = "Invalid Input"
            }
        }

        buttonClear.setOnClickListener {
            currentNumber = ""
            input.text = ""
            output.text = ""
        }

        buttonBackSpace.setOnClickListener {
            if (currentNumber.isNotEmpty()) {
                currentNumber = currentNumber.dropLast(1)
                input.text = currentNumber
            }
        }

        buttonCE.setOnClickListener {
            input.text = ""
        }

        buttonComma.setOnClickListener {
            currentNumber += "."
            input.text = currentNumber
        }
    }

    private fun performOperation(operation: String) {
        currentNumber += operation
        input.text = currentNumber
    }
}
