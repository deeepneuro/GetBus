package com.company.getbus

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.company.getbus.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase



class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var firebaseAnalytics: FirebaseAnalytics


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




        val modify_1 = "1"
        val modify_2 = "2"
        val modify_3 = "3"







        binding.button1.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_NAME, "button_1")
            }
            val first_intent = Intent(this, First::class.java)
            first_intent.putExtra("modify", modify_1)
            startActivity(first_intent)




        }

        binding.button2.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_NAME, "button_2")
            }
            val first_intent = Intent(this, First::class.java)
            first_intent.putExtra("modify", modify_2)
            startActivity(first_intent)

        }

        binding.button3.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_NAME, "button_3")
            }
            val first_intent = Intent(this, First::class.java)
            first_intent.putExtra("modify", modify_3)
            startActivity(first_intent)
           
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





