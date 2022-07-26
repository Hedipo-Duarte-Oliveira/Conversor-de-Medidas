package br.com.ocannha.conversordemedidas

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import br.com.ocannha.conversordemedidas.model.CalculationStrategyHold
import br.com.ocannha.conversordemedidas.model.Calculator
import br.com.ocannha.conversordemedidas.model.startegies.KilometerCentimenters
import br.com.ocannha.conversordemedidas.model.startegies.KilometerToMeterStrategy
import br.com.ocannha.conversordemedidas.model.startegies.MetersToCentimeterStrategy
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var editValue: EditText
    private lateinit var spConversion: Spinner
    val supportedCalculationStrategies = arrayOf(
        CalculationStrategyHold("Quilometros para centimentros", KilometerCentimenters()),
        CalculationStrategyHold("Quilometros para metros", KilometerToMeterStrategy()),
        CalculationStrategyHold("Metros para centimentros", MetersToCentimeterStrategy()),
        CalculationStrategyHold("Metros para quilometros", MetersToCentimeterStrategy())

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var value = 0.0
        var position = 0

        savedInstanceState?.let {
            value = it.getDouble("VALUE")
            position = it.getInt("POSITION")
        }

        initi()
        setUi(value,position)
        setActions()
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        try {
            outState.putDouble("VALUE", editValue.text.toString().toDouble())
        }catch (e: NumberFormatException){

        }
        outState.putInt("POSITION", spConversion.selectedItemPosition)

    }

    private fun initi() {
        spConversion = findViewById(R.id.sp_conversions)
        editValue = findViewById(R.id.edit_value)
    }

    private fun setActions() {
        val btnConverte: Button = findViewById(R.id.BtnConverter)

        btnConverte.setOnClickListener {
        }

        val btnClean: Button = findViewById(R.id.btn_clean)
        btnClean.setOnClickListener {
            editValue.setText("")
            editValue.error = ""

            clean()

            spConversion.setSelection(0)

            try {
                val value = editValue.text.toString().toDouble()
                val calculationStratetegy = supportedCalculationStrategies[
                    spConversion.selectedItemPosition
                ].calculationStrategy

                Calculator.setCalculationStratetegy(
                    calculationStratetegy
                )

                val result = Calculator.calculate(value)
                val label = calculationStratetegy.getResultLabel(result != 1.toDouble())

                showResult(result, label)
            } catch (e: NumberFormatException) {
                editValue.error = "Valor inválido"
                editValue.requestFocus()
            }
        }
    }

    private fun clean() {
        editValue.setText("")
        editValue.error = null
    }

    private fun showResult(result: Double, label: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("RESULT", result)
        intent.putExtra("LABEL", label)

        startActivity(intent)
    }

    private fun setUi(value: Double,position: Int){
        editValue.setText(value.toString())

        val spAdapter = ArrayAdapter(this, R.layout.res_spinner_item, getSpinnerData())
        spAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spConversion.adapter = spAdapter
        spConversion.setSelection(position)

        }

    private fun getSpinnerData(): MutableList<String> {
        val spinnerData = mutableListOf<String>()
        supportedCalculationStrategies.forEach {
            spinnerData.add(it.nome)
        }
        return spinnerData
    }
}
