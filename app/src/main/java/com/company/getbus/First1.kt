package com.company.getbus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.company.getbus.databinding.ActivityFirst1Binding
import com.google.android.gms.ads.MobileAds
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest

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

        ////////////////////////////////////////////////////////  yandex banner  ///////////////////////
        MobileAds.initialize(this) {}

        val banner = findViewById<BannerAdView>(R.id.banner3)
        banner.setAdUnitId("R-M-2089625-3")
        banner.setAdSize(AdSize.stickySize(320))

        val adRequest = AdRequest.Builder().build()

        banner.loadAd(adRequest)
/////////////////////////////////////////////////////////////////////////////////////////////////



        val modify = this.intent.getStringExtra("modify")
        val direct = this.intent.getStringExtra("direct")
        val work = "work"
        val holy = "holy"



        binding.buttonWorky.setOnClickListener {
            binding.buttonWorky.animate().apply {
                duration = 150
                scaleXBy(0.1f)

            }.withEndAction(){
                binding.buttonWorky.animate().apply {
                    duration = 150
                    scaleXBy(-0.1f)
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                val first11_intent = Intent(this, First1_1::class.java)
                first11_intent.putExtra("modify", modify)
                first11_intent.putExtra("direct", direct)
                first11_intent.putExtra("work_holy", work)

                startActivity(first11_intent)
            }, 400)

        }

        binding.buttonHoly.setOnClickListener {
            binding.buttonHoly.animate().apply {
                duration = 150
                scaleXBy(0.1f)

            }.withEndAction(){
                binding.buttonHoly.animate().apply {
                    duration = 150
                    scaleXBy(-0.1f)
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                val first11_intent = Intent(this, First1_1::class.java)
                first11_intent.putExtra("modify", modify)
                first11_intent.putExtra("direct", direct)
                first11_intent.putExtra("work_holy", holy)

                startActivity(first11_intent)
            }, 400)

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

