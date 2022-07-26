package br.com.ocannha.conversordemedidas.model

import br.com.ocannha.conversordemedidas.model.startegies.CalculationStrategy

object Calculator {

    private var calculationStrategy: CalculationStrategy? = null

    fun setCalculationStratetegy(calculationStrategy: CalculationStrategy) {
        this.calculationStrategy = calculationStrategy
    }

    // se a estrategia nao estiver definida vai ser enviado uma excexão uma mensa
    fun calculate(value: Double): Double {
        if (calculationStrategy == null) {
            throw IllegalAccessException("Calculation Strategy is not set ")
        }

        return calculationStrategy!!.calculate(value)
    }
}
