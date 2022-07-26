package br.com.ocannha.conversordemedidas.model

import br.com.ocannha.conversordemedidas.model.startegies.CalculationStrategy

data class CalculationStrategyHold (
    val nome : String,
            val calculationStrategy: CalculationStrategy
)