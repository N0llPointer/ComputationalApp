package com.nollpointer.computationalapp.view

import android.content.Context
import android.support.v7.widget.CardView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.nollpointer.computationalapp.R

class ComputationCard(context: Context): CardView(context){

    private val resultTextView: TextView
    private val firstValueEditText: EditText
    private val secondValueEditText: EditText
    private val thirdValueEditText: EditText

    init {
        val view = View.inflate(context, R.layout.computation_cell, null)
        addView(view)

        firstValueEditText = view.findViewById(R.id.input_a)
        secondValueEditText = view.findViewById(R.id.input_b)
        thirdValueEditText = view.findViewById(R.id.input_l)

        resultTextView = view.findViewById(R.id.result_text_view)
    }



    fun calculate(){
        val a = firstValueEditText.text.toString().toDouble()
        val b = secondValueEditText.text.toString().toDouble()
        val l = thirdValueEditText.text.toString().toDouble()

        val catSpeed = l*(a+b)/(2*a*b)
        val mouseSpeed = l*(a-b)/(2*a*b)

        resultTextView.text = "Средняя скорость кошки: $catSpeed м/с\n" +
                "Средняя скорость мышки: $mouseSpeed м/с"
    }

}
