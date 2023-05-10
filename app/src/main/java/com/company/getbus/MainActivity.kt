package com.company.getbus

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.company.getbus.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest


class MainActivity : AppCompatActivity() {



    override fun onBackPressed() { //переопределяет стоковое действие кнопки BACK /// в данном случае осуществляет выход из приложения onDestroy
        super.onBackPressed()

        val handler = Handler() //задержка system.exit для полного отображения анимации
        handler.postDelayed(Runnable {

            System.exit(0)

        }, 500)


    }


    lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var mAdView : AdView

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics                                                          //аналитика
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {                               //
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
            param(FirebaseAnalytics.Param.SCREEN_NAME, Intent.ACTION_CALL)
        }
                                                                                                                   //
        /*firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {                                //
            param(FirebaseAnalytics.Param.ITEM_ID, "taxiButton")                                         //
            param(FirebaseAnalytics.Param.ITEM_NAME, "click")                                           //
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")                                        //
        }                                                                                         ///////
        val parameters = Bundle().apply {                                                   ///////
            this.putString("level_name", "Caverns01")                                  ///////
            this.putInt("level_difficulty", 4)                                        ///////
                                                                                  ///////
        }                                                                    ///////
                                                                        ///////
        firebaseAnalytics.setDefaultEventParameters(parameters)*/  /////////
//////////////////////////////////////////////////////////////////

        val sharedPreferences: SharedPreferences                            ///// считает количество запусков приложения после установки
        sharedPreferences = getPreferences(0)                      /////
        var count = sharedPreferences.getInt("numRun", 0)              /////
        count++                                                     /////
        sharedPreferences.edit().putInt("numRun", count).apply() /////
                            ///////////////////////////////////////



        binding = ActivityMainBinding.inflate(layoutInflater)

        supportActionBar?.hide() // отключает ActionBar
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.blue) //меняет цвет statusBar
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  // меняет цвет шрифта в statusBar
        setContentView(binding.root)

        ////////////////////////////////////////////////////////  yandex banner  ///////////////////////
        MobileAds.initialize(this) {}

        val banner = findViewById<BannerAdView>(R.id.banner)
        banner.setAdUnitId("R-M-2089625-1")
        banner.setAdSize(AdSize.stickySize(320))

        val adRequest = AdRequest.Builder().build()

        banner.loadAd(adRequest)
/////////////////////////////////////////////////////////////////////////////////////////////////



        val modify_1 = "1"
        val modify_2 = "2"
        val modify_3 = "3"


        val density = resources.displayMetrics.run { density }
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        val addDigit = 40.44117 * density
        binding.imageView11?.animate()?.apply {
            duration = 1000
            translationX(width.toFloat() / 2 + addDigit.toFloat())
        }




        binding.button1.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_NAME, "button_1")
            }
            binding.button1.animate().apply {
                duration = 150
                scaleYBy(0.1f)

            }.withEndAction(){
                binding.button1.animate().apply {
                    duration = 150
                    scaleYBy(-0.1f)
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                val first_intent = Intent(this, First::class.java)
                first_intent.putExtra("modify", modify_1)
                startActivity(first_intent)

            }, 400)




        }

        binding.button2.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_NAME, "button_2")
            }
            binding.button2.animate().apply {
                duration = 150
                scaleYBy(0.1f)

            }.withEndAction(){
                binding.button2.animate().apply {
                    duration = 150
                    scaleYBy(-0.1f)
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                val first_intent = Intent(this, First::class.java)
                first_intent.putExtra("modify", modify_2)
                startActivity(first_intent)
            }, 400)


        }

        binding.button3.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_NAME, "button_3")
            }
            binding.button3.animate().apply {
                duration = 150
                scaleYBy(0.1f)

            }.withEndAction(){
                binding.button3.animate().apply {
                    duration = 150
                    scaleYBy(-0.1f)
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                val first_intent = Intent(this, First::class.java)
                first_intent.putExtra("modify", modify_3)
                startActivity(first_intent)
            }, 400)



           
        }

        if (count > 1){ //считает количество запусков приложения
            binding.StartButton!!.isGone = true //убираем элементы UI при втором и последующем запуске
            binding.StartrText!!.isGone = true
            binding.startText!!.isGone = true
            binding.checkBox!!.isGone = true

        }else if (count == 1) {

            binding.button1.isClickable = false //кнапки не кликабельны при первом запуске приложения после установки
            binding.button2.isClickable = false
            binding.button3.isClickable = false


            binding.StartButton!!.isEnabled = false // кнопка не активна и подсвечена серым цветом!!

            binding.checkBox!!.setOnCheckedChangeListener { buttonView, isChecked -> // слушатель чекбокса
                if (isChecked) {
                    binding.StartButton!!.isEnabled = true // активирует кнопку или деактивирует
                }else{
                    binding.StartButton!!.isEnabled = false
                }
            }

            binding.StartButton!!.setOnClickListener {

                binding.StartButton!!.isGone = true
                binding.StartrText!!.isGone = true
                binding.button1.isClickable = true
                binding.button2.isClickable = true
                binding.button3.isClickable = true
                binding.checkBox!!.isGone = true
                binding.startText!!.isGone = true

            }
        }

    }








}





