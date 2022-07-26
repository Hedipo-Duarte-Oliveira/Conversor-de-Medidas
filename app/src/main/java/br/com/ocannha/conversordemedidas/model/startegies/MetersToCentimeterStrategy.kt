package br.com.ocannha.conversordemedidas.model.startegies

class MetersToCentimeterStrategy : CalculationStrategy {
    override fun calculate(value: Double): Double = value * 100

    override fun getResultLabel(isPlural: Boolean): String =
        if (isPlural) "Centimetros" else "Centimentros"
}
