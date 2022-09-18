package com.company.getbus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.company.getbus.databinding.ActivityFirst1Binding

class First1: AppCompatActivity() {

    private lateinit var binding: ActivityFirst1Binding


    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirst1Binding.inflate(layoutInflater)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this@First1, R.color.blue) //меняет цвет statusBar
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  // меняет цвет шрифта в statusBar
        val view = binding.root
        setContentView(view)



        val modify = this.intent.getStringExtra("modify")
        val direct = this.intent.getStringExtra("direct")
        val work = "work"
        val holy = "holy"



        binding.buttonWorky.setOnClickListener {
            val first11_intent = Intent(this, First1_1::class.java)
            first11_intent.putExtra("modify", modify)
            first11_intent.putExtra("direct", direct)
            first11_intent.putExtra("work_holy", work)

            startActivity(first11_intent)
        }

        binding.buttonHoly.setOnClickListener {
            val first11_intent = Intent(this, First1_1::class.java)
            first11_intent.putExtra("modify", modify)
            first11_intent.putExtra("direct", direct)
            first11_intent.putExtra("work_holy", holy)

            startActivity(first11_intent)
        }
    }


}





















    /*val timeUp = this.intent.getStringExtra("timeUp")
        val timeDown = this.intent.getStringExtra("timeDown")

        button_worky.text = "12"
        button_worky.setOnClickListener {
            val first11_intent = Intent (this, First1_1::class.java)
            first11_intent.putExtra("timeUp", timeUp.toString())
            first11_intent.putExtra("timeDown", timeDown.toString())
            startActivity(first11_intent)
        }*/

