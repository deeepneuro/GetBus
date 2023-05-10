package com.company.getbus

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.company.getbus.databinding.ActivityFirstBinding
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest


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



        ////////////////////////////////////////////////////////  yandex banner  ///////////////////////
        MobileAds.initialize(this) {}

        val banner = findViewById<BannerAdView>(R.id.banner2)
        banner.setAdUnitId("R-M-2089625-2")
        banner.setAdSize(AdSize.stickySize(320))

        val adRequest = AdRequest.Builder().build()

        banner.loadAd(adRequest)
/////////////////////////////////////////////////////////////////////////////////////////////////



        val direct_up = "up"
        val direct_down = "down"
        val modify = this.intent.getStringExtra("modify")
        if (modify == "1"){
            binding.buttonCtt.text = "из ЦТТ в ЖД"
            binding.buttonZhd.text = "из ЖД в ЦТТ"
        }




        binding.buttonCtt.setOnClickListener {
            binding.buttonCtt.animate().apply {
                duration = 150
                scaleXBy(0.1f)

            }.withEndAction(){
                binding.buttonCtt.animate().apply {
                    duration = 150
                    scaleXBy(-0.1f)
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                val first1_intent = Intent(this, First1::class.java)
                first1_intent.putExtra("direct", direct_down)
                first1_intent.putExtra("modify", modify)

                startActivity(first1_intent)
            }, 400)

        }

        binding.buttonZhd.setOnClickListener {
            binding.buttonZhd.animate().apply {
                duration = 150
                scaleXBy(0.1f)

            }.withEndAction(){
                binding.buttonZhd.animate().apply {
                    duration = 150
                    scaleXBy(-0.1f)
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                val first1_intent = Intent(this, First1::class.java)
                first1_intent.putExtra("direct", direct_up)
                first1_intent.putExtra("modify", modify)

                startActivity(first1_intent)
            }, 400)

        }


    }
}

