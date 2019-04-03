package com.nollpointer.computationalapp.view

import android.content.Context
import android.support.v7.widget.CardView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.nollpointer.computationalapp.CardController
import com.nollpointer.computationalapp.R

class TriangleCard(context: Context): CardView(context), CardController {

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


    fun calculate(){
        val a = firstValueEditText.text.toString().toDouble()
        val b = secondValueEditText.text.toString().toDouble()
        val c = thirdValueEditText.text.toString().toDouble()

        if(c < a || c < b)
            resultTextView.text = "Правильный треугольник в сечении невозможен"
        else{
            if(a == b) {
                val result = Math.sqrt(c * c - a * a)
                resultTextView.text = "AN = BM = $result"
            }else
                resultTextView.text = "Правильный треугольник в сечении невозможен"
        }
    }

//    fun calculateResult(a: Double, b: Double, c: Double): Pair<Double,Double>{
//        var endResult: Double
//        while()
//
//    }
}