package com.company.getbus

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.company.getbus.databinding.ActivityFirstBinding
import com.google.firebase.analytics.FirebaseAnalytics


class First: AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide() // скрывает action bar
        window.statusBarColor = ContextCompat.getColor(this@First, R.color.blue) //меняет цвет statusBar
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  // меняет цвет шрифта в statusBar
        setContentView(view)

        FirebaseAnalytics.Param.SCREEN_CLASS

        val direct_up = "up"
        val direct_down = "down"
        val modify = this.intent.getStringExtra("modify")
        if (modify == "1"){
            binding.buttonCtt.text = "из ЦТТ в ЖД"
            binding.buttonZhd.text = "из ЖД в ЦТТ"
        }




        binding.buttonCtt.setOnClickListener {
            val first1_intent = Intent(this, First1::class.java)
            first1_intent.putExtra("direct", direct_down)
            first1_intent.putExtra("modify", modify)

            startActivity(first1_intent)
        }

        binding.buttonZhd.setOnClickListener {
            val first1_intent = Intent(this, First1::class.java)
            first1_intent.putExtra("direct", direct_up)
            first1_intent.putExtra("modify", modify)

            startActivity(first1_intent)
        }





















        /*val timingBus1_1: List<Double> = listOf(06.05, 06.25, 06.40, 06.45, 07.15, 07.25, 07.35, 07.45, 07.55, 08.10, 08.15,
            08.25, 08.35, 08.45, 08.55, 09.15, 09.30, 09.40, 10.00, 10.20, 10.40, 11.00, 11.20,11.40, 12.05, 12.25, 12.40,
            12.50, 13.00, 13.10, 13.25, 13.40, 13.50, 14.05, 14.20,12.55, 13.10, 13.20, 13.30, 13.45, 14.00, 14.10, 14.25,
            14.40, 17.25, 17.40, 18.05,18.25, 18.50, 19.20, 19.50)

        val timeNow = 10.10
        val timeUp: MutableList<Double> = mutableListOf()
        val timeDown: MutableList<Double> = mutableListOf()
        for (n in timingBus1_1){
            if (n < timeNow){
                timeUp.add(n)
            }else{timeDown.add(n)}
        }


        button_ctt_zhd.setOnClickListener {

            val first1_intent = Intent (this, First1::class.java)
            first1_intent.putExtra("timeUp", timeUp.last().toString())
            first1_intent.putExtra("timeDown", timeDown.first().toString())
            startActivity(first1_intent)




        }*/
    }
}

