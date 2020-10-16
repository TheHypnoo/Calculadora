package com.example.calculadora

import android.content.Context
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

    private lateinit var button0: Button
    private lateinit var NomMoneda: Button
    private lateinit var buttonComa: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonCE: Button
    private lateinit var buttonDEL: Button
    private lateinit var buttonIgual: Button
    private lateinit var input: TextView
    private lateinit var output: TextView


    private lateinit var buttonDolar: Button
    private lateinit var buttonLliura: Button
    private lateinit var buttonYen: Button
    private lateinit var buttonYuan: Button

    private var conversioDolar: Double = 1.0
    private var conversioLliura: Double = 1.0
    private var conversioYen: Double = 1.0
    private var conversioYuan: Double = 1.0
    private var conversioActual: Double = 1.0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE); //Deixa de mostrar el Action Bar
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
        resetCalculadora()
        CalculatorColorOutInt()
    }

    private fun resetCalculadora(){
        input.text = "0"
        output.text = "0"
        CalculatorColorOutInt()
    }

    private fun initViews(){
        button0 = findViewById(R.id.btn0)
        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)
        button5 = findViewById(R.id.btn5)
        button6 = findViewById(R.id.btn6)
        button7 = findViewById(R.id.btn7)
        button8 = findViewById(R.id.btn8)
        button9 = findViewById(R.id.btn9)
        buttonCE = findViewById(R.id.btnCE)
        buttonDEL = findViewById(R.id.btnDEL)
        buttonComa = findViewById(R.id.btnComa)
        buttonIgual = findViewById(R.id.btnIgual)
        input = findViewById(R.id.inputDiners)
        output = findViewById(R.id.outputDiners)


        buttonDolar = findViewById(R.id.btnDolar)
        buttonLliura = findViewById(R.id.btnLibra)
        buttonYen = findViewById(R.id.btnYen)
        buttonYuan = findViewById(R.id.btnYuan)


    }

    private fun initListeners(){

        button0.setOnClickListener { addNumero(0)

        }
        button1.setOnClickListener { addNumero(1)

        }
        button2.setOnClickListener { addNumero(2)

        }
        button3.setOnClickListener { addNumero(3)

        }
        button4.setOnClickListener { addNumero(4)

        }
        button5.setOnClickListener { addNumero(5)

        }
        button6.setOnClickListener { addNumero(6) }
        button7.setOnClickListener { addNumero(7) }
        button8.setOnClickListener { addNumero(8) }
        button9.setOnClickListener { addNumero(9) }
        buttonYen.setOnClickListener { setYenListener() }
        buttonCE.setOnClickListener { resetCalculadora() }
        buttonDEL.setOnClickListener { if (input.length() != 1)input.text = input.text.substring(0, input.length()-1) else input.text = "0" }
        buttonComa.setOnClickListener { if (!checkComa()){ input.text = "${input.text}," } }
        buttonIgual.setOnClickListener { igualar() }
        buttonDolar.setOnClickListener { setDolarListener()   }
        buttonLliura.setOnClickListener { setLliuraListener() }
        buttonYuan.setOnClickListener { setYuanListener() }



    }

    private fun addNumero(numero: Int) {

        val posicio = input.text.toString().lastIndexOf(",")
        if (input.length() < 12) {
            if (posicio > -1) {
                if (input.text.substring(posicio, input.text.length).length < 3) {
                    input.text = if (input.text != "0")"${input.text}$numero" else numero.toString()
                }
            } else {
                input.text = if (input.text != "0")"${input.text}$numero" else numero.toString()
            }
        }
    }

    private fun CalculatorColorOutInt(){
        if(output.text != "0") {
            //output.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            output.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        } else {
            output.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        }

        if(input.text != "0") {
            //output.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            input.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        } else {
            input.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        }
    }



    private fun setYenListener() {
        if (conversioYen == 1.0) {
            //crear builder crear parametres
            val builder = AlertDialog.Builder(this)
            //crear un edittext per codi
            val editText = EditText(this)
            //creem un layout per codi, es el container del editText
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            //assigname al layout al editText
            editText.layoutParams = lp
            //demanar numeros, o numeros amb decimals
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            //titol del dialeg
            builder.setTitle("Introdueix el valor de conversió del yen")
            //posem editText + layout dins del lalert
            builder.setView(editText)
            //                                          variables que retorna la funcio(OnClick en java)
            builder.setPositiveButton("Aceptar") { position, which ->
                //fer aixo si no dona error
                try {
                    //primer toString, despres passem de string a double
                    conversioYen = editText.text.toString().toDouble()
                    //iguala la conversio a una variable per utilitzarla a la funcio igualar.
                    conversioActual = conversioYen
                    //salta quan hi ha un error
                } catch (e: Exception) {
                    //alerta       context: la pantalla on et trobes, fa referencia a aquesta activity
                    Toast.makeText(this, "Input no vàlid", Toast.LENGTH_SHORT).show()
                }
            }
            //                                          null perque no fa res o no hi ha res
            builder.setNegativeButton("Cancelar", null)
            // a partir de la plantilla creada(builder) creem el dialeg
            val dialog = builder.create()
            //.show() per mostrar, si no no va
            dialog.show()
        } else {

            conversioActual = conversioYen
        }
        highlightSelected(YEN)
    }

    private fun setDolarListener() {
        if (conversioDolar == 1.0) {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            editText.layoutParams = lp
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            builder.setTitle("Introdueix el valor de conversió del dòlar")
            builder.setView(editText)
            builder.setPositiveButton("Aceptar") { position, which ->
                try {
                    conversioDolar = editText.text.toString().toDouble()
                    conversioActual = conversioDolar
                } catch (e: Exception) {
                    Toast.makeText(this, "Input no vàlid", Toast.LENGTH_SHORT).show()
                }

            }
            builder.setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.show()
        } else {
            conversioActual = conversioDolar
        }
        highlightSelected(DOLAR)
    }

    private fun setYuanListener() {

        if (conversioYuan == 1.0) {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            editText.layoutParams = lp
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            builder.setTitle("Introdueix el valor de conversió del yuan")
            builder.setView(editText)
            builder.setPositiveButton("Aceptar") { position, which ->
                try {
                    conversioYuan = editText.text.toString().toDouble()
                    conversioActual = conversioYuan
                } catch (e: Exception) {
                    Toast.makeText(this, "Input no vàlid", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.show()
        } else {
            conversioActual = conversioYuan
        }
        highlightSelected(YUAN)
    }


    private fun setLliuraListener() {
        if (conversioLliura == 1.0) {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            editText.layoutParams = lp
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            builder.setTitle("Introdueix el valor de conversió de la lliura")
            builder.setView(editText)
            builder.setPositiveButton("Aceptar") { position, which ->
                try {
                    conversioLliura = editText.text.toString().toDouble()
                    conversioActual = conversioLliura
                } catch (e: Exception) {
                    Toast.makeText(this, "Input no vàlid", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.show()
        } else {
            conversioActual = conversioLliura
        }
        highlightSelected(LLIURA)
    }


    private fun igualar() {
        if (conversioActual == 1.0) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alerta!")
            builder.setMessage("No has escollit cap moneda per fer la conversió")
            val dialog = builder.create()
            dialog.show()
        } else {
            //.replace reemplaça lo que hi ha en oldChar per lo del newChar
            val comaToPoint = input.text.toString().replace(',','.')
            var resultat = comaToPoint.toDouble()*conversioActual

            if (resultat % 1.0 != 0.0) {
                output.text = truncate(resultat.toString(), 2)!!.replace('.',',')
            } else output.text = resultat.toInt().toString()
            CalculatorColorOutInt()
        }
    }

    private fun truncate(value: String, places: Int): String? {
        return BigDecimal(value).setScale(places, RoundingMode.DOWN).stripTrailingZeros().toString()
    }

    private fun checkComa(): Boolean {
        return input.text.contains(",")
    }


    private fun highlightSelected(divisa: String) {
        buttonDolar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        buttonLliura.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        buttonYen.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        buttonYuan.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        val escollit = when(divisa) {
            DOLAR -> buttonDolar
            LLIURA -> buttonLliura
            YEN -> buttonYen
            YUAN -> buttonYuan
            else -> return
        }
        escollit.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
    }
}