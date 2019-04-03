package com.nollpointer.computationalapp.view

import android.content.Context
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.nollpointer.computationalapp.CardController
import com.nollpointer.computationalapp.R
import kotlin.math.roundToInt

class NaturalNumbersComputationCard(context: Context): CardView(context), CardController {

    private val resultListView: ListView
    private val firstValueEditText: EditText

    init {
        val view = View.inflate(context, R.layout.natural_numbers_card, null)
        addView(view)

        firstValueEditText = view.findViewById(R.id.input_a)

        resultListView = view.findViewById(R.id.result_list_view)
    }



    fun calculate(){
        val max = firstValueEditText.text.toString().toInt()
        val numberList = mutableListOf<String>()
        for(number in 1..max){
            var value = number
            do{
                val a = value%10
                if(a == 0)
                    break;
                if(number % a != 0)
                    break;
                value /= 10
            }while(value > 0)

            if(value == 0)
                numberList.add(number.toString())
        }

        resultListView.adapter = ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,numberList.toTypedArray())

    }

}
