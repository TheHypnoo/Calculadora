package com.thehypnoo.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import java.lang.Exception
import java.math.BigDecimal
import java.math.RoundingMode

private const val DOLAR = "dolar"
private const val LLIURA = "lliura"
private const val YEN = "yen"
private const val YUAN = "yuan"

class MainActivity : AppCompatActivity() {

    private lateinit var btn0: Button
    private lateinit var btnComa: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnCE: Button
    private lateinit var btnDEL: Button
    private lateinit var btnIgual: Button
    private lateinit var inputText: TextView
    private lateinit var outputText: TextView


    private lateinit var btnDolar: Button
    private lateinit var btnLliura: Button
    private lateinit var btnYen: Button
    private lateinit var btnYuan: Button

    private var conversionDolar: Double = 1.0
    private var conversionLliura: Double = 1.0
    private var conversionYen: Double = 1.0
    private var conversionYuan: Double = 1.0
    private var conversionActual: Double = 1.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
        resetCalculadora()
        CalculatorColorOutInt()
    }

    private fun resetCalculadora(){
        inputText.text = "0"
        outputText.text = "0"
        CalculatorColorOutInt()
    }

    private fun initViews(){
        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btnCE = findViewById(R.id.btnCE)
        btnDEL = findViewById(R.id.btnDEL)
        btnComa = findViewById(R.id.btnComa)
        btnIgual = findViewById(R.id.btnIgual)
        inputText = findViewById(R.id.inputCoins)
        outputText = findViewById(R.id.outputCoins)

        btnDolar = findViewById(R.id.btnDolar)
        btnLliura = findViewById(R.id.btnLibra)
        btnYen = findViewById(R.id.btnYen)
        btnYuan = findViewById(R.id.btnYuan)
    }

    @SuppressLint("SetTextI18n")
    private fun initListeners(){

        btn0.setOnClickListener { addNumber(0) }
        btn1.setOnClickListener { addNumber(1) }
        btn2.setOnClickListener { addNumber(2) }
        btn3.setOnClickListener { addNumber(3) }
        btn4.setOnClickListener { addNumber(4) }
        btn5.setOnClickListener { addNumber(5) }
        btn6.setOnClickListener { addNumber(6) }
        btn7.setOnClickListener { addNumber(7) }
        btn8.setOnClickListener { addNumber(8) }
        btn9.setOnClickListener { addNumber(9) }

        btnCE.setOnClickListener { resetCalculadora() }
        btnDEL.setOnClickListener { if (inputText.length() != 1)inputText.text = inputText.text.substring(0, inputText.length()-1) else inputText.text = "0" }
        btnComa.setOnClickListener { if (!checkComa()){ inputText.text = "${inputText.text}," } }
        btnIgual.setOnClickListener { igualar() }

        btnYen.setOnClickListener { setYenListener() }
        btnDolar.setOnClickListener { setDolarListener() }
        btnLliura.setOnClickListener { setLliuraListener() }
        btnYuan.setOnClickListener { setYuanListener() }
    }

    private fun addNumber(numero: Int) {
        val posicio = inputText.text.toString().lastIndexOf(",")
        if (inputText.length() < 12) {
            if (posicio > -1) {
                if (inputText.text.substring(posicio, inputText.text.length).length < 3) {
                    inputText.text = if (inputText.text != "0")"${inputText.text}$numero" else numero.toString()
                }
            } else {
                inputText.text = if (inputText.text != "0")"${inputText.text}$numero" else numero.toString()
            }
        }
    }

    private fun CalculatorColorOutInt(){
        if(outputText.text != "0") {
            outputText.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        } else {
            outputText.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        }

        if(inputText.text != "0") {
            inputText.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        } else {
            inputText.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        }
    }

    private fun setYenListener() {
        if (conversionYen == 1.0) {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            val Layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            editText.layoutParams = Layout
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            builder.setTitle("Introdueix el valor de conversió del yen")
            builder.setView(editText)
            builder.setPositiveButton("Aceptar") { position, which ->
                try {
                    conversionYen = editText.text.toString().toDouble()
                    conversionActual = conversionYen
                } catch (e: Exception) {
                    Toast.makeText(this, "No vàlid", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.show()
        } else {
            conversionActual = conversionYen
        }
        backgroundBottonCoins(YEN)
    }

    private fun setDolarListener() {
        if (conversionDolar == 1.0) {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            val Layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            editText.layoutParams = Layout
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            builder.setTitle("Introdueix el valor de conversió del dòlar")
            builder.setView(editText)
            builder.setPositiveButton("Aceptar") { position, which ->
                try {
                    conversionDolar = editText.text.toString().toDouble()
                    conversionActual = conversionDolar
                } catch (e: Exception) {
                    Toast.makeText(this, "No vàlid", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.show()
        } else {
            conversionActual = conversionDolar
        }
        backgroundBottonCoins(DOLAR)
    }

    private fun setYuanListener() {
        if (conversionYuan == 1.0) {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            val Layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            editText.layoutParams = Layout
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            builder.setTitle("Introdueix el valor de conversió del yuan")
            builder.setView(editText)
            builder.setPositiveButton("Aceptar") { position, which ->
                try {
                    conversionYuan = editText.text.toString().toDouble()
                    conversionActual = conversionYuan
                } catch (e: Exception) {
                    Toast.makeText(this, "No vàlid", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.show()
        } else {
            conversionActual = conversionYuan
        }
        backgroundBottonCoins(YUAN)
    }

    private fun setLliuraListener() {
        if (conversionLliura == 1.0) {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            val Layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            editText.layoutParams = Layout
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            builder.setTitle("Introdueix el valor de conversió de la lliura")
            builder.setView(editText)
            builder.setPositiveButton("Aceptar") { position, which ->
                try {
                    conversionLliura = editText.text.toString().toDouble()
                    conversionActual = conversionLliura
                } catch (e: Exception) {
                    Toast.makeText(this, "No vàlid", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.show()
        } else {
            conversionActual = conversionLliura
        }
        backgroundBottonCoins(LLIURA)
    }

    private fun igualar() {
        if (conversionActual == 1.0) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Warning!")
            builder.setMessage("No has escollit cap moneda per fer la conversió")
            val dialog = builder.create()
            dialog.show()
        } else {
            val comaToPoint = inputText.text.toString().replace(',','.')
            val resultat = comaToPoint.toDouble()*conversionActual

            if (resultat % 1.0 != 0.0) {
                outputText.text = truncate(resultat.toString(), 2)!!.replace('.',',')
            } else outputText.text = resultat.toInt().toString()
            CalculatorColorOutInt()
        }
    }

    private fun truncate(value: String, places: Int): String {
        return BigDecimal(value).setScale(places, RoundingMode.DOWN).stripTrailingZeros().toString()
    }

    private fun checkComa(): Boolean {
        return inputText.text.contains(",")
    }

    private fun backgroundBottonCoins(divisa: String) {

        btnDolar.setBackgroundColor(ContextCompat.getColor(this,android.R.color.holo_orange_dark))
        btnLliura.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        btnYen.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        btnYuan.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        val escollit = when(divisa) {
            DOLAR -> btnDolar
            LLIURA -> btnLliura
            YEN -> btnYen
            YUAN -> btnYuan
            else -> return
        }
        escollit.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
    }
}