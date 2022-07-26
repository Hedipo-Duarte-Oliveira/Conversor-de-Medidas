package br.com.ocannha.conversordemedidas.model.startegies

class KilometerCentimenters : CalculationStrategy {
    override fun calculate(value: Double): Double = value * 100_000

    override fun getResultLabel(isPlural: Boolean): String =
        if (isPlural) "Centimetros" else "Centimentro"
}
