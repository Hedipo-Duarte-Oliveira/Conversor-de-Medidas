package br.com.ocannha.conversordemedidas.model.startegies

class MeterToKilomterStrategy : CalculationStrategy {
    override fun calculate(value: Double): Double = value / 1_000

    override fun getResultLabel(isPlural: Boolean): String =
        if (isPlural) "Quilômetros" else " Quilômetro"
}
