package br.com.ocannha.conversordemedidas.model.startegies



interface CalculationStrategy {

    fun calculate(value: Double): Double

     fun getResultLabel(isPlural: Boolean): String = if (isPlural) "metros" else "metro"
}