package com.nollpointer.computationalapp.view

import android.content.Context
import android.support.v7.widget.CardView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.nollpointer.computationalapp.R

class TriangleCard(context: Context) : CardView(context) {

    private val resultTextView: TextView
    private val firstValueEditText: EditText
    private val secondValueEditText: EditText
    private val thirdValueEditText: EditText

    init {
        val view = View.inflate(context, R.layout.triangle_cell, null)
        addView(view)

        firstValueEditText = view.findViewById(R.id.input_a)
        secondValueEditText = view.findViewById(R.id.input_b)
        thirdValueEditText = view.findViewById(R.id.input_c)

        resultTextView = view.findViewById(R.id.result_text_view)
    }


    fun calculate() {
        val a = firstValueEditText.text.toString().toDouble()
        val b = secondValueEditText.text.toString().toDouble()
        val c = thirdValueEditText.text.toString().toDouble()

        if (c < a || c < b)
            resultTextView.text = "Правильный треугольник в сечении невозможен"
        else {
            val AN = Math.sqrt(
                (a * a + c * c - 2 * b * b + 2 * Math.sqrt(
                    Math.pow(a, 4.0) + Math.pow(
                        b,
                        4.0
                    ) + Math.pow(c, 4.0) + b * b * c * c - a * a * b * b - a * a * c * c
                )) / 3
            )

            val BM = Math.sqrt(
                (b * b + c * c - 2 * a * a + 2 * Math.sqrt(
                    Math.pow(a, 4.0) + Math.pow(
                        b,
                        4.0
                    ) + Math.pow(c, 4.0) + b * b * c * c - a * a * b * b - a * a * c * c
                )) / 3
            )

            resultTextView.text = "AN = $AN\n" +
                    "BN = $BM"
//            if(a == b) {
//                val result = Math.sqrt(c * c - a * a)
//                resultTextView.text = "AN = BM = $result"
//            }else
//                resultTextView.text = "Правильный треугольник в сечении невозможен"
        }
    }

//    fun calculateResult(a: Double, b: Double, c: Double): Pair<Double,Double>{
//        var endResult: Double
//        while()
//
//    }
}