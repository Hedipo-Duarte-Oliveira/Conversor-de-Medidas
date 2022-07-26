package br.com.ocannha.conversordemedidas

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val result = intent.getDoubleExtra("RESULT", 0.0)
        val label = intent.getStringExtra("LABEL")

        val tvValue: TextView = findViewById(R.id.tv_value)
        tvValue.text = result.toString()

        val tvValueLabel: TextView = findViewById(R.id.tv_valueLabel)
        tvValueLabel.text = label

        val btnClose: Button = findViewById(R.id.btn_close)
        btnClose.setOnClickListener {
            finish()
        }
    }
}
