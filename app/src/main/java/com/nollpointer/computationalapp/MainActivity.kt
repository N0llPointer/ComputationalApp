package com.nollpointer.computationalapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.nollpointer.computationalapp.view.ComputationCard
import com.nollpointer.computationalapp.view.NaturalNumbersComputationCard
import com.nollpointer.computationalapp.view.TriangleCard
import com.nollpointer.computationalapp.view.TriangleComputationCard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager

    lateinit var triangleCard: TriangleCard
    lateinit var computationCard: ComputationCard
    lateinit var naturalNumbersComputationCard: NaturalNumbersComputationCard
    lateinit var triangleComputationCard: TriangleComputationCard


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 23)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        viewPager = findViewById(R.id.viewPager)

        val adapter = InfoCardAdapter(this, getInfoCards())
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                if (p0 < 2)
                    toolbar.title = "11 лаб. работа"
                else
                    toolbar.title = "12 лаб. работа"
            }
        })

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculate()
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_info -> showInfoDialog()
            }
            true
        }
    }

    private fun calculate() {
        when (viewPager.currentItem) {
            0 -> computationCard.calculate();
            1 -> triangleCard.calculate();
            2 -> naturalNumbersComputationCard.calculate();
            3 -> triangleComputationCard.calculate();
        }

    }


    private fun getInfoCards(): List<CardView> {
        computationCard = ComputationCard(this)
        triangleCard = TriangleCard(this)
        naturalNumbersComputationCard = NaturalNumbersComputationCard(this)
        triangleComputationCard = TriangleComputationCard(this)

        return mutableListOf(computationCard, triangleCard, naturalNumbersComputationCard, triangleComputationCard)
    }

    private fun showInfoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Информация")
        val info = "Сделал: <u>Онанов Алексей</u><br>" +
                "Группа: <u>ПМИ-б-о-17-1</u>"
        builder.setMessage(Html.fromHtml(info))
        builder.setPositiveButton("Ок") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }


    class InfoCardAdapter(val context: Context, val infoCardsList: List<CardView>) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            container.addView(infoCardsList[position])
            return infoCardsList[position]

        }

        override fun isViewFromObject(p0: View, p1: Any) = p0 == p1

        override fun getCount() = infoCardsList.size

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}
