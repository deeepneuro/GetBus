package com.company.getbus

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import com.company.getbus.databinding.ActivityFirst11Binding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.MobileAds
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime
import java.util.*




@Suppress("ImplicitThis")
class First1_1: AppCompatActivity() {

    private lateinit var binding: ActivityFirst11Binding
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    val CALL_PHONE_RQ = 101


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        firebaseAnalytics = Firebase.analytics //инициализация firebase

        /*firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "First1_1")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, ACTION_CALL)

            //param(FirebaseAnalytics.Param.VALUE, "IJMPTcV3TqeQA5XV-snsvA")
        }*/


        binding = ActivityFirst11Binding.inflate(layoutInflater) //фрагмент
        
        supportActionBar?.hide() //скрывает ActionBar
        window.statusBarColor = ContextCompat.getColor(this@First1_1, R.color.blue) //меняет цвет statusBar
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  // меняет цвет шрифта в statusBar
        val view = binding.root
        setContentView(view)


        ////////////////////////////////////////////////////////  yandex banner  ///////////////////////
        MobileAds.initialize(this) {}

        val banner = findViewById<BannerAdView>(R.id.banner4)
        banner.setAdUnitId("R-M-2089625-4")
        banner.setAdSize(AdSize.stickySize(320))

        val adRequest = AdRequest.Builder().build()

        banner.loadAd(adRequest)
/////////////////////////////////////////////////////////////////////////////////////////////////


        val destiny = resources.displayMetrics.run { density }
        //val destinydpi = resources.displayMetrics.run { densityDpi }// dpi дисплея
        //val widthView2 = binding.imageView2.getDrawable().getIntrinsicWidth()
        //val widthDp = resources.displayMetrics.run { widthPixels } // ширина дисплея в пикселах
        //val heightDp = resources.displayMetrics.run { heightPixels / density } // высота дисплея в dp
        //binding.textView6.text = widthDp.toString()



        val modify = this.intent.getStringExtra("modify") // 1 или 2 или 3
        val direct = this.intent.getStringExtra("direct") // up или down
        val work_holy = this.intent.getStringExtra("work_holy") // work или holy


        val params = binding.shedule.layoutParams as ViewGroup.MarginLayoutParams //перемещение кнопки кодом в зависимосити от условий
        val params1 = binding.taxiButton.layoutParams as ViewGroup.MarginLayoutParams  //перемещение кнопки кодом в зависимосити от условий


        binding.taxiButton.setOnClickListener {
            checkForPermission(android.Manifest.permission.CALL_PHONE, "phone", CALL_PHONE_RQ)
            firebaseAnalytics = Firebase.analytics //инициализация firebase
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_UP) {                                //
                param(FirebaseAnalytics.Param.ITEM_NAME, "taxiButton")                                         //
            }//проверка на наличие разрешений
        //val intent = Intent(ACTION_CALL, Uri.parse("tel:" + "+79524049959"))
            //startActivity(intent)
        }










/* /////////////////////////////////////////////////////   2   ////////////////////////////////////////////////////// */
        if (modify == "2") {
            binding.numA.text = "2"
            binding.numB.text = "2"
            if (direct == "up") {
                binding.point1.text = "зпд.мкр."
                binding.point2.text = "парковая"
                binding.point3.text = "унтем"
                binding.point4.text = "а/вокзал"
                binding.point5.text = "цтт"
            }else{
                binding.point1.text = "цтт"
                binding.point2.text = "центр"
                binding.point3.text = "м/комб."
                binding.point4.text = "лесхоз"
                binding.point5.text = "зпд.мкр."
            }


            val timeNow = SimpleDateFormat("HH.mm") //получаем дату в простом 24.00 формате
            val timeNowFormated = timeNow.format(Date()) // форматируем полученные данные
            val timeNowDouble = timeNowFormated.toDouble() // приводим к Double



            val one_down_work:MutableList<Double> = mutableListOf(5.00, 5.30,      6.00, 6.30, 7.00, 7.15, 7.30, 8.00, 8.30, 9.00, 9.30, 10.00, 10.30, 11.00, 11.30, 12.00, 12.30, 13.00, 13.15, 13.30, 14.00, 14.30, 15.00, 15.30, 16.00, 16.30, 17.00, 17.30, 18.00, 18.30, 19.00, 19.30, 20.10, 21.30,      22.00, 23.59, 0.00, 5.59)//после пробелов значение мнимое
            val one_up_work:MutableList<Double> = mutableListOf(5.30, 6.00,      6.30, 7.00, 7.30, 7.45, 8.00, 8.30, 9.00, 9.30, 10.00, 10.30, 11.00, 11.30, 12.00, 12.30, 13.00, 13.30, 13.45, 14.00, 14.30, 15.00, 15.30, 16.00, 16.30, 17.00, 17.30, 18.00, 18.30, 19.00, 19.30, 20.00, 20.40, 22.00,      22.30, 23.59, 0.00, 6.29)//последнее значение мнимое
            val one_down_holy:MutableList<Double> = mutableListOf(5.15, 5.45,     6.15, 6.45, 7.15, 7.45, 8.15, 8.45, 9.30, 10.00, 10.40, 11.10, 11.40, 12.10, 12.40, 13.15, 13.45, 14.15, 15.00, 15.30, 16.05, 16.50, 17.30, 18.00, 18.30, 19.00, 19.30, 20.10, 21.30,     22.00, 23.59, 0.00, 6.14)//последнее значение мнимое
            val one_up_holy:MutableList<Double> = mutableListOf(5.45, 6.15,      6.45, 7.15, 7.45, 8.15, 8.45, 9.15, 10.00, 10.30, 11.10, 11.40, 12.10, 12.40, 13.10, 13.45, 14.15, 14.45, 15.30, 16.00, 16.35, 17.20, 18.00, 18.30, 19.00, 19.30, 20.00, 20.40, 22.00,      22.30, 23.59, 0.00, 6.49)//последнее значение мнимое
            val virtual_revers:MutableList<Double> = mutableListOf(6.00, 6.30,         7.00, 7.30, 8.00, 8.15, 8.30, 9.00, 9.30, 10.00, 10.30, 11.00, 11.30, 12.00, 12.30, 13.00, 13.30, 14.00, 14.15, 14.30, 15.00, 15.30, 16.00, 16.30, 17.00, 17.30, 18.00, 18.30, 19.00, 19.30, 20.00, 20.30, 21.10, 22.30,     23.00, 23.59, 0.00, 6.44)
            val virtual_revers_2:MutableList<Double> = mutableListOf(6.15, 6.45,     7.15, 7.45, 8.15, 8.45, 9.15, 9.45, 10.30, 11.00, 11.40, 12.10, 12.40, 13.10, 13.40, 14.15, 14.45, 15.15, 16.00, 16.30, 17.05, 17.50, 18.30, 19.00, 19.30, 20.00, 20.30, 21.10, 22.30,       3.0, 3.0, 3.0, 3.0)//виртуальный список для выходных
            var beforeXX = 0.0
            var beforeX = 0.0
            var pastX = 0.0
            var indxi = 0
            var indxii = 0
            var revers_time = 0.0
            var revers_time_before = 0.0
            var LenOfList = 0
            var firstRace = 0.0 //значение первого рейса
            var currentTimeList:MutableList<Double> = mutableListOf()

            if (direct == "down" && work_holy == "work") {

                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_2_d_w")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////
                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag2w.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()

                    }, 400)



                }




                for (i in one_down_work) {
                    if (i > timeNowDouble && timeNowDouble >= one_down_work[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_down_work.size  //количество элементов в коллекции
                        indxi = one_down_work.indexOf(i) - 1
                        indxii = one_down_work.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_down_work
                        beforeX = one_down_work[indxi]
                        beforeXX = one_down_work[indxii]
                        revers_time = one_up_work[indxi]
                        revers_time_before =one_up_work[indxii]
                        firstRace = currentTimeList[0]
                        break
                    } else {
                        currentTimeList = one_down_work
                        continue
                    }
                }
            }else if (direct == "up" && work_holy == "work"){
                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_2_u_w")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            //.setCustomAnimations(R.anim.slide_in, R.anim.slide_out) анимация только при открытии фрагмента
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag2w.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)

                }
                for (i in one_up_work) {
                    if (i > timeNowDouble && timeNowDouble >= one_up_work[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_up_work.size
                        indxi = one_up_work.indexOf(i) - 1
                        indxii = one_up_work.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_up_work
                        beforeX = one_up_work[indxi]
                        beforeXX = one_up_work[indxii]
                        revers_time = virtual_revers[indxi]
                        revers_time_before = virtual_revers[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_up_work
                        continue
                    }
                }
            }else if (direct == "down" && work_holy == "holy"){
                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_2_d_h")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag2h.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)

                }
                for (i in one_down_holy) {
                    if (i > timeNowDouble && timeNowDouble >= one_down_holy[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_down_holy.size
                        indxi = one_down_holy.indexOf(i) - 1
                        indxii = one_down_holy.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_down_holy
                        beforeX = one_down_holy[indxi]
                        beforeXX = one_down_holy[indxii]
                        revers_time = one_up_holy[indxi]
                        revers_time_before = one_up_holy[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_down_holy
                        continue
                    }
                }
            }else if(direct == "up" && work_holy == "holy"){
                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_2_u_h")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag2h.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 300)

                }
                for (i in one_up_holy) {
                    if (i > timeNowDouble && timeNowDouble >= one_up_holy[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_up_holy.size
                        indxi = one_up_holy.indexOf(i) - 1
                        indxii = one_up_holy.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_up_holy
                        beforeX = one_up_holy[indxi]
                        beforeXX = one_up_holy[indxii]
                        revers_time = virtual_revers_2[indxi]
                        revers_time_before = virtual_revers_2[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_up_holy
                        continue
                    }
                }
            }



            //парсинг значений времени

            val revers_time_beforeF = String.format(Locale.CHINA, "%.2f", revers_time_before)
            val revers_timeF = String.format(Locale.CHINA, "%.2f", revers_time)
            val pastF = String.format(Locale.CHINA, "%.2f", pastX)//добавляет хвостовые нули после точки
            val beforeF = String.format(Locale.CHINA, "%.2f", beforeX) //добавляет хвостовые нули после точки
            val beforeXXF = String.format(Locale.CHINA, "%.2f", beforeXX)

            val time_now_F_list: List<String> = timeNowFormated.split(".") //разбиваем значение настоящего времени на часы и минуты и кладем их в список
            val aTNF = time_now_F_list[0] //кладем в переменную значение "часы"
            val bTNF = time_now_F_list[1] //кладем в переменную значение "минуты"
            val pastF_list: List<String> = pastF.split(".") //разбиваем значение "рейс после" на часы и минуты
            val aPastRace = pastF_list[0] //кладем в переменную значение "часы"
            val bPastRace = pastF_list[1] //кладем в переменную значение "минуты"
            val beforeF_list: List<String> = beforeF.split(".") //разбиваем значение "рейс до" на часы и минуты
            val aBeforeRace = beforeF_list[0] //кладем в переменную значение "часы"
            val bBeforeRace = beforeF_list[1] //кладем в переменную значение "минуты"
            val beforeXXF_list: List<String> = beforeXXF.split(".")
            val aBXX = beforeXXF_list[0]
            val bBXX = beforeXXF_list[1]
            val revers_time_list: List<String> = revers_timeF.split(".")
            val aReversRace = revers_time_list[0]
            val bReversRace = revers_time_list[1]
            val revers_time_before_list: List<String> = revers_time_beforeF.split(".")
            val aReversRaceBefore = revers_time_before_list[0]
            val bReversRaceBefore = revers_time_before_list[1]






            //блок определения временных интервалов между рейсами в различных последовательностях
            val timeNowForCompare = LocalTime.of(aTNF.toInt(), bTNF.toInt())//кладем настоящее время для дальнейшего сравнения
            val timeReversRaceBefore = LocalTime.of(aReversRaceBefore.toInt(), bReversRaceBefore.toInt())//кладем время дважды предыдущего обратного рейса
            val timeReverseRace = LocalTime.of(aReversRace.toInt(), bReversRace.toInt())//кладем значение часа и минут рейса из обратного направления для последующего сравнения
            val timeBeforeRace = LocalTime.of(aBeforeRace.toInt(), bBeforeRace.toInt())//кладем значение часа и минут предыдущего рейса для последующего сравнения
            val timeBeforeXX = LocalTime.of(aBXX.toInt(), bBXX.toInt() )//кладем значение часа и минут дважды предыдущего рейса для последующего сравнения
            val timePastRace = LocalTime.of(aPastRace.toInt(), bPastRace.toInt()) // значения часа и минут последующего рейса для дальнейшего сравнения


            val timeStartStop = Duration.between(timeBeforeRace, timeReverseRace)//получаем время продолжительности рейса. сравнивается с виртуальным временем
            val timeBeforeDoubleBefore = Duration.between(timeBeforeXX, timeBeforeRace)//получаем время между рейсом "до" и дважды "до"
            val timeStartStopBefore = Duration.between(timeBeforeXX, timeReversRaceBefore)//получаем время дважды предыдущего рейса старт стоп
            val timeBeforePast = Duration.between(timeBeforeRace, timePastRace) //получаем время между рейсом "до" и рейсом "после"
            val timeBeforeNow = Duration.between(timeBeforeRace, timeNowForCompare)//продолжительность между "до" и "сейчас"
            val timeDoubleBeforeNow = Duration.between(timeBeforeXX, timeNowForCompare)//продолжительность между дважды "до" и "сейчас"
            val timeNowPast = Duration.between(timeNowForCompare, timePastRace)// продолжительность между "сейчас" и рейсом "после"

            var StartStop = timeStartStop.toMinutes().toInt()//в минутах старт стоп
            val BeforeDoubleBefore = timeBeforeDoubleBefore.toMinutes().toInt()//в минутах между дважды "до" и "до"
            val StartStopBefore = timeStartStopBefore.toMinutes().toInt()// в минутах пердыдущий старт стоп
            val BeforePast = timeBeforePast.toMinutes().toInt()//в минутах между рейсом "до" и рейсом "после"
            val differenceBeforeNow = timeBeforeNow.toMinutes().toInt()//в минутах между "до" и "сейчас"
            val differenceBeforeXXNow = timeDoubleBeforeNow.toMinutes().toInt()//в минутах между дважды "до" и "сейчас"
            val differenceNowPast = timeNowPast.toMinutes().toInt()// в минутах между "сейчас" и "после"





/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/

            if (timeNowDouble == 23.59 || timeNowDouble == 0.00){

                params.topMargin = (117*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий


                params1.topMargin = (117*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton

                val formatTime = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
                binding.textView5.text = "автобус будет утром в $formatTime"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                return
            }

            if (timeNowDouble > 0.00 && timeNowDouble < currentTimeList[2]){

                params.topMargin = (120*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (120*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton


                val formatTime = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
                binding.textView5.text = "автобус выйдет на маршрут в $formatTime"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                return
            }
/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/




            //параметры анимации
            val widthView2 = binding.imageView2.getDrawable().getIntrinsicWidth()// получаем ширину imageView2 - длинна пути
            val kf = 1.85f // коэффициет соотношения между  Dp и Px

            val x = 0f
            val z = (((widthView2 / StartStop) * (differenceBeforeNow)).toFloat())//продолжительность анимации по длинне по y или x. это значение dp, для правильной анимации на всех устройствах нужно в атрибутах выставить выставить px
            val z2 = (((widthView2 / StartStopBefore) * (differenceBeforeXXNow)).toFloat())
            val value_duration = 60000 * 0.05 //продолжительность анимации по времени
            val animator = ValueAnimator.ofFloat(x, z / kf)//значения аниматора представлены переменными
            val animator2 = ValueAnimator.ofFloat(x, z2 / kf)
            animator.interpolator = AccelerateDecelerateInterpolator()      //задает ускорение или замедление анимации или отключает
            animator.duration = value_duration.toLong()    //продолжительность анимации
            animator2.interpolator = AccelerateDecelerateInterpolator()      //задает ускорение или замедление анимации или отключает
            animator2.duration = value_duration.toLong()      //продолжительность анимации
            animator.start()
            animator2.start()

            if (z2 > widthView2 && z < widthView2){ //определяем видимость или невидимость второго автобуса

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView7.isVisible = false
                binding.numB.isVisible = false
                binding.textView7.marginTop
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0){
                    binding.textView5.text ="рейс $beforeF в пути меньше минуты"
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }else{
                    binding.textView5.text ="рейс $beforeF в пути $differenceBeforeNow мин."
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }

            }else if(z > widthView2 && z2 > widthView2){ //при таких условиях автобусов на линии нет

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView.isVisible = false
                binding.textView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView6.isVisible = false
                binding.textView7.isVisible = false
                binding.textView14.isVisible = true
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                binding.textView5.text = "рейс $pastF выйдет на маршрут через $differenceNowPast мин."

            }else {

                params.topMargin = (315*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (315*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = true //определяем видимость первого автобуса и соответствующих текстов
                binding.textView13.isVisible = true
                binding.textView13.text = beforeXXF
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0) {
                    binding.textView6.text = "рейс $beforeXXF в пути $differenceBeforeXXNow мин."
                    binding.textView5.text = "рейс $beforeF в пути меньше минуты"
                    binding.textView7.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                } else {
                    binding.textView6.text = "рейс $beforeXXF в пути $differenceBeforeXXNow мин."
                    binding.textView5.text = "рейс $beforeF в пути $differenceBeforeNow мин"
                    binding.textView7.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                }
            }


            if (timeNowDouble >= currentTimeList[2] && timeNowDouble < currentTimeList[3]){

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView6.isVisible = true
                binding.textView7.isVisible = false
                binding.numB.isVisible = false
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0){
                    binding.textView5.text ="рейс $beforeF в пути меньше минуты"
                    binding.textView6.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                }else{
                    binding.textView5.text ="рейс $beforeF в пути $differenceBeforeNow мин"
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }
            }





            if ((LenOfList - 4) == (indxi + 1)){  //последний рейс

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                if (differenceBeforeNow == 0){
                    binding.textView5.text = "рейс $beforeF в пути меньше минуты"
                    binding.textView6.text = "сегодня этот рейс последний"
                }else{
                    binding.textView5.text = "рейс $beforeF в пути ${differenceBeforeNow} мин."
                    binding.textView6.text = "сегодня этот рейс последний"
                }
            }

            val firstOfList = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
            if (timeNowDouble >= currentTimeList[LenOfList - 4] && timeNowDouble < 23.59) {

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.textView5.text = "движение автобусов на сегодня завершено"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
            }




            animator.addUpdateListener(object:
                ValueAnimator.AnimatorUpdateListener {  //аниматор для imageView
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    val animatedValue = animation?.animatedValue as Float
                    binding.imageView.translationX = animatedValue
                    binding.textView4.translationX = animatedValue
                    binding.numA.translationX = animatedValue



                }
            })
            animator2.addUpdateListener(object:
                ValueAnimator.AnimatorUpdateListener { //аниматор для imageView4
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    val animatedValue = animation?.animatedValue as Float
                    binding.imageView4.translationX = animatedValue
                    binding.textView13.translationX = animatedValue
                    binding.numB.translationX = animatedValue


                }
            })
        }




        /* ////////////////////////////////////////////////////// 1 ///////////////////////////////////////////////////////////////////////// */


        if (modify == "1") {
            binding.numA.text = "1"
            binding.numB.text = "1"
            if (direct == "up") {
                binding.point1.text = "жд/вокзал"
                binding.point2.text = "ветеран"
                binding.point3.text = "цетр"
                binding.point4.text = "атп"
                binding.point5.text = "цтт"
            }else{
                binding.point1.text = "цтт"
                binding.point2.text = "атп"
                binding.point3.text = "центр"
                binding.point4.text = "ветеран"
                binding.point5.text = "жд/вокзал"
            }



            val timeNow = SimpleDateFormat("HH.mm") //получаем дату в простом 24.00 формате
            val timeNowFormated = timeNow.format(Date()) // форматируем полученные данные
            val timeNowDouble = timeNowFormated.toDouble() // приводим к Double




                   /*  !!!!! внимание !!!могут быть проблемы с индексами!!!  здесь добавлены 6,20 и 6,40 они виртуальные для правильной работы первого рейса*/
            val one_down_work:MutableList<Double> = mutableListOf(6.00, 6.20,      6.40, 6.45, 7.00, 7.15, 7.25, 7.35, 7.45, 7.55, 8.10, 8.15, 8.25, 8.35, 8.45, 8.55, 9.15, 9.30, 9.40, 10.00, 10.20, 10.40, 11.00, 11.20, 11.40, 12.05, 12.25, 12.40, 12.50, 13.00, 13.10, 13.25, 13.40, 13.50, 14.05, 14.20, 14.30, 14.45, 15.00, 15.20, 15.40, 16.00, 16.20, 16.40, 17.00, 17.25, 17.40, 18.05, 18.25, 18.50, 19.20, 19.50,   20.10, 23.59, 0.00, 6.39)//после пробелов значение мнимое
            val one_up_work:MutableList<Double> = mutableListOf(6.20, 6.40,      7.00, 7.05, 7.20, 7.35, 7.45, 7.55, 8.05, 8.15, 8.30, 8.35, 8.45, 8.55, 9.05, 9.15, 9.35, 9.45, 10.00, 10.20, 10.40, 11.05, 11.20, 11.40, 12.00, 12.25, 12.45, 12.55, 13.10, 13.20, 13.30, 13.45, 14.00, 14.10, 14.25, 14.40, 14.50, 15.05, 15.20, 15.40, 16.05, 16.20, 16.40, 17.05, 17.20, 17.45, 18.00, 18.20, 18.45, 19.05, 19.40, 20.10,   20.30, 23.59, 0.00, 6.59)//последнее значение мнимое
            val one_down_holy:MutableList<Double> = mutableListOf(6.20, 6.40,     7.00, 7.20, 7.40, 8.00, 8.20, 8.40, 9.00, 9.40, 10.00, 10.25, 10.40, 11.05, 11.20, 11.45, 12.00, 12.25, 12.40, 13.05, 13.20, 13.40, 14.00, 14.20, 14.40, 15.00, 15.20, 16.00, 16.20, 16.40, 17.00, 17.20, 17.40, 18.00, 18.20, 18.40, 19.00, 19.25,   19.45, 23.59, 0.00, 6.39)//последнее значение мнимое
            val one_up_holy:MutableList<Double> = mutableListOf(6.40, 7.00,      7.20, 7.40, 8.00, 8.20, 8.40, 9.00, 9.20, 10.00, 10.20, 10.45, 11.05, 11.25, 11.40, 12.05, 12.20, 12.45, 13.00, 13.25, 13.40, 14.00, 14.20, 14.40, 15.00, 15.20, 15.40, 16.20, 16.40, 17.00, 17.20, 17.40, 18.00, 18.20, 18.40, 19.00, 19.15, 19.45,   20.05, 23.59, 0.00, 6.59)//последнее значение мнимое
            val virtual_revers:MutableList<Double> = mutableListOf(6.35, 6.55,      7.15, 7.25, 7.35, 7.55,	8.05, 8.15, 8.25, 8.35, 8.45, 8.55, 9.05, 9.15, 9.25, 9.35, 9.55, 10.00, 10.20, 10.40, 11.00, 11.20, 11.40, 12.00, 12.20, 12.40, 13.00, 13.10, 13.25, 13.40, 13.50, 14.05, 14.20, 14.30, 14.45, 15.00, 15.10, 15.25, 15.40, 16.00, 16.20, 16.40, 17.00, 17.25, 17.40, 18.05, 18.20, 18.40, 19.05, 19.25, 20.00, 20.30,  3.0, 3.0, 3.0, 3.0)
            val virtual_revers_2:MutableList<Double> = mutableListOf(7.00, 7.20,     7.40, 8.00, 8.20, 8.40, 9.00, 9.20, 9.40, 10.20, 10.40, 11.05, 11.20, 11.45, 12.00, 12.25, 12.40, 13.05, 13.20, 13.40, 14.00, 14.20, 14.40, 15.00, 15.20, 15.40, 16.00, 16.40, 17.00, 17.20, 17.40, 18.00, 18.20, 18.40, 19.00, 19.20, 19.35, 20.05,  3.0, 3.0, 3.0, 3.0)//виртуальный список для выходных
            var beforeXX = 0.0
            var beforeX = 0.0
            var pastX = 0.0
            var indxi = 0
            var indxii = 0
            var revers_time = 0.0
            var revers_time_before = 0.0
            var LenOfList = 0
            var firstRace = 0.0 //значение первого рейса
            var currentTimeList:MutableList<Double> = mutableListOf()






            if (direct == "down" && work_holy == "work") {
            /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_1_d_w")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)

                }




                for (i in one_down_work) {
                    if (i > timeNowDouble && timeNowDouble >= one_down_work[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_down_work.size  //количество элементов в коллекции
                        indxi = one_down_work.indexOf(i) - 1
                        indxii = one_down_work.indexOf(i) - 2//вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_down_work
                        beforeX = one_down_work[indxi]
                        beforeXX = one_down_work[indxii]
                        revers_time = one_up_work[indxi]
                        revers_time_before =one_up_work[indxii]
                        firstRace = currentTimeList[0]
                        break
                    } else {
                        currentTimeList = one_down_work
                        continue
                    }
                }
            }else if (direct == "up" && work_holy == "work"){
                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_1_u_w") //отправляет в аналитику строковое заначение
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)
                }

                for (i in one_up_work) {
                    if (i > timeNowDouble && timeNowDouble >= one_up_work[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_up_work.size
                        indxi = one_up_work.indexOf(i) - 1
                        indxii = one_up_work.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_up_work
                        beforeX = one_up_work[indxi]
                        beforeXX = one_up_work[indxii]
                        revers_time = virtual_revers[indxi]
                        revers_time_before = virtual_revers[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_up_work
                        continue
                    }
                }
            }else if (direct == "down" && work_holy == "holy"){
                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_1_d_h")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag1h.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)
                }
                for (i in one_down_holy) {
                    if (i > timeNowDouble && timeNowDouble >= one_down_holy[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_down_holy.size
                        indxi = one_down_holy.indexOf(i) - 1
                        indxii = one_down_holy.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_down_holy
                        beforeX = one_down_holy[indxi]
                        beforeXX = one_down_holy[indxii]
                        revers_time = one_up_holy[indxi]
                        revers_time_before = one_up_holy[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_down_holy
                        continue
                    }
                }
            }else if(direct == "up" && work_holy == "holy"){
                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_1_u_h")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag1h.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)
                }
                for (i in one_up_holy) {
                    if (i > timeNowDouble && timeNowDouble >= one_up_holy[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_up_holy.size
                        indxi = one_up_holy.indexOf(i) - 1
                        indxii = one_up_holy.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_up_holy
                        beforeX = one_up_holy[indxi]
                        beforeXX = one_up_holy[indxii]
                        revers_time = virtual_revers_2[indxi]
                        revers_time_before = virtual_revers_2[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_up_holy
                        continue
                    }
                }
            }




            //парсим значения времени

            val revers_time_beforeF = String.format(Locale.CHINA, "%.2f", revers_time_before)
            val revers_timeF = String.format(Locale.CHINA, "%.2f", revers_time)
            val pastF = String.format(Locale.CHINA, "%.2f", pastX)//добавляет хвостовые нули после точки
            val beforeF = String.format(Locale.CHINA, "%.2f", beforeX) //добавляет хвостовые нули после точки
            val beforeXXF = String.format(Locale.CHINA, "%.2f", beforeXX)
            val time_now_F_list: List<String> = timeNowFormated.split(".") //разбиваем значение настоящего времени на часы и минуты и кладем их в список
            val aTNF = time_now_F_list[0] //кладем в переменную значение "часы"
            val bTNF = time_now_F_list[1] //кладем в переменную значение "минуты"
            val pastF_list: List<String> = pastF.split(".") //разбиваем значение "рейс после" на часы и минуты
            val aPastRace = pastF_list[0] //кладем в переменную значение "часы"
            val bPastRace = pastF_list[1] //кладем в переменную значение "минуты"
            val beforeF_list: List<String> = beforeF.split(".") //разбиваем значение "рейс до" на часы и минуты
            val aBeforeRace = beforeF_list[0] //кладем в переменную значение "часы"
            val bBeforeRace = beforeF_list[1] //кладем в переменную значение "минуты"
            val beforeXXF_list: List<String> = beforeXXF.split(".")
            val aBXX = beforeXXF_list[0]
            val bBXX = beforeXXF_list[1]
            val revers_time_list: List<String> = revers_timeF.split(".")
            val aReversRace = revers_time_list[0]
            val bReversRace = revers_time_list[1]
            val revers_time_before_list: List<String> = revers_time_beforeF.split(".")
            val aReversRaceBefore = revers_time_before_list[0]
            val bReversRaceBefore = revers_time_before_list[1]




            //блок определения временных интервалов между рейсами в различных последовательностях
            val timeNowForCompare = LocalTime.of(aTNF.toInt(), bTNF.toInt())//кладем настоящее время для дальнейшего сравнения
            val timeReversRaceBefore = LocalTime.of(aReversRaceBefore.toInt(), bReversRaceBefore.toInt())//кладем время дважды предыдущего обратного рейса
            val timeReverseRace = LocalTime.of(aReversRace.toInt(), bReversRace.toInt())//кладем значение часа и минут рейса из обратного направления для последующего сравнения
            val timeBeforeRace = LocalTime.of(aBeforeRace.toInt(), bBeforeRace.toInt())//кладем значение часа и минут предыдущего рейса для последующего сравнения
            val timeBeforeXX = LocalTime.of(aBXX.toInt(), bBXX.toInt() )//кладем значение часа и минут дважды предыдущего рейса для последующего сравнения
            val timePastRace = LocalTime.of(aPastRace.toInt(), bPastRace.toInt()) // значения часа и минут последующего рейса для дальнейшего сравнения


            val timeStartStop = Duration.between(timeBeforeRace, timeReverseRace)//получаем время продолжительности рейса. сравнивается с виртуальным временем
            val timeBeforeDoubleBefore = Duration.between(timeBeforeXX, timeBeforeRace)//получаем время между рейсом "до" и дважды "до"
            val timeStartStopBefore = Duration.between(timeBeforeXX, timeReversRaceBefore)//получаем время дважды предыдущего рейса старт стоп
            val timeBeforePast = Duration.between(timeBeforeRace, timePastRace) //получаем время между рейсом "до" и рейсом "после"
            val timeBeforeNow = Duration.between(timeBeforeRace, timeNowForCompare)//продолжительность между "до" и "сейчас"
            val timeDoubleBeforeNow = Duration.between(timeBeforeXX, timeNowForCompare)//продолжительность между дважды "до" и "сейчас"
            val timeNowPast = Duration.between(timeNowForCompare, timePastRace)// продолжительность между "сейчас" и рейсом "после"

            val StartStop = timeStartStop.toMinutes().toInt()//в минутах старт стоп
            val BeforeDoubleBefore = timeBeforeDoubleBefore.toMinutes().toInt()//в минутах между дважды "до" и "до"
            val StartStopBefore = timeStartStopBefore.toMinutes().toInt()// в минутах пердыдущий старт стоп
            val BeforePast = timeBeforePast.toMinutes().toInt()//в минутах между рейсом "до" и рейсом "после"
            val differenceBeforeNow = timeBeforeNow.toMinutes().toInt()//в минутах между "до" и "сейчас"
            val differenceBeforeXXNow = timeDoubleBeforeNow.toMinutes().toInt()//в минутах между дважды "до" и "сейчас"
            val differenceNowPast = timeNowPast.toMinutes().toInt()// в минутах между "сейчас" и "после"
            




/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/

            if (timeNowDouble == 23.59 || timeNowDouble == 0.00){ //исключительные временные сценарии и их описание работы

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                val formatTime = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
                binding.textView5.text = "автобус выйдет на маршрут в $formatTime"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                return
            }

            if (timeNowDouble > 0.00 && timeNowDouble < currentTimeList[2]){

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                val formatTime = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
                binding.textView5.text = "автобус выйдет на маршрут в $formatTime"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                return
            }
/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/


            //параметры анимации
            val widthView2 = binding.imageView2.getDrawable().getIntrinsicWidth()// получаем ширину imageView2 - длинна пути
            val kf: Float = 1.85f // коэффициет соотношения между  Dp и Px

            val x = 0f
            val z = (((widthView2 / StartStop) * (differenceBeforeNow)).toFloat())//продолжительность анимации по длинне по y или x. это значение dp, для правильной анимации на всех устройствах нужно в атрибутах выставить выставить px
            val z2 = (((widthView2 / StartStopBefore) * (differenceBeforeXXNow)).toFloat())
            val value_duration = 60000 * 0.05 //продолжительность анимации по времени
            val animator = ValueAnimator.ofFloat(x, z / kf)//значения аниматора представлены переменными
            val animator2 = ValueAnimator.ofFloat(x, z2 / kf)
            animator.interpolator = AccelerateDecelerateInterpolator()      //задает ускорение или замедление анимации или отключает
            animator.duration = value_duration.toLong()    //продолжительность анимации
            animator2.interpolator = AccelerateDecelerateInterpolator()      //задает ускорение или замедление анимации или отключает
            animator2.duration = value_duration.toLong()      //продолжительность анимации
            animator.start()
            animator2.start()

            if (z2 > widthView2 && z < widthView2){ //определяем видимость или невидимость второго автобуса

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView7.isVisible = false
                binding.numB.isVisible = false
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0){
                    binding.textView5.text ="рейс $beforeF в пути меньше минуты"
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }else{
                    binding.textView5.text ="рейс $beforeF в пути $differenceBeforeNow мин."
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }

            }else if(z > widthView2 && z2 > widthView2){ //при таких условиях автобусов на линии нет

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView.isVisible = false
                binding.textView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView6.isVisible = false
                binding.textView7.isVisible = false
                binding.textView14.isVisible = true
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                binding.textView5.text = "рейс $pastF выйдет на маршрут через $differenceNowPast мин."

            }else {

                params.topMargin = (315*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (315*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = true //определяем видимость первого автобуса и соответствующих текстов
                binding.textView13.isVisible = true
                binding.textView13.text = beforeXXF
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0) {
                    binding.textView6.text = "рейс $beforeXXF в пути $differenceBeforeXXNow мин."
                    binding.textView5.text = "рейс $beforeF в пути меньше минуты"
                    binding.textView7.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                } else {
                    binding.textView6.text = "рейс $beforeXXF в пути $differenceBeforeXXNow мин."
                    binding.textView5.text = "рейс $beforeF в пути $differenceBeforeNow мин"
                    binding.textView7.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                }
            }


            if (timeNowDouble >= currentTimeList[2] && timeNowDouble < currentTimeList[3]){

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView6.isVisible = true
                binding.textView7.isVisible = false
                binding.numB.isVisible = false
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0){
                    binding.textView5.text ="рейс $beforeF в пути меньше минуты"
                    binding.textView6.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                }else{
                    binding.textView5.text ="рейс $beforeF в пути $differenceBeforeNow мин"
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }
            }





            if ((LenOfList - 4) == (indxi + 1)){//последний рейс

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                if (differenceBeforeNow == 0){
                    binding.textView5.text = "рейс $beforeF в пути меньше минуты"
                    binding.textView6.text = "сегодня этот рейс последний"
                }else{
                    binding.textView5.text = "рейс $beforeF в пути ${differenceBeforeNow} мин."
                    binding.textView6.text = "сегодня этот рейс последний"
                }
            }

            val firstOfList = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
            if (timeNowDouble >= currentTimeList[LenOfList - 4] && timeNowDouble < 23.59) {

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.textView5.text = "движение автобусов на сегодня завершено"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
            }





            animator.addUpdateListener(object :
                ValueAnimator.AnimatorUpdateListener {  //аниматор для imageView
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    val animatedValue = animation?.animatedValue as Float
                    binding.imageView.translationX = animatedValue
                    binding.textView4.translationX = animatedValue
                    binding.numA.translationX = animatedValue
                }
            })




            animator2.addUpdateListener(object :
                ValueAnimator.AnimatorUpdateListener { //аниматор для imageView4
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    val animatedValue = animation?.animatedValue as Float
                    binding.imageView4.translationX = animatedValue
                    binding.textView13.translationX = animatedValue
                    binding.numB.translationX = animatedValue
                }
            })

        }



        /* ////////////////////////////////////////// 3 /////////////////////////////////////////////////////////////////// */
        if (modify == "3") {
            binding.numA.text = "3"
            binding.numB.text = "3"
            if (direct == "up") {
                binding.point1.text = "зпд.мкр"
                binding.point2.text = "маяк"
                binding.point3.text = "школьная"
                binding.point4.text = "сад"
                binding.point5.text = "цтт"
            }else{
                binding.point1.text = "цтт"
                binding.point2.text = "сад"
                binding.point3.text = "школьная"
                binding.point4.text = "лесхоз"
                binding.point5.text = "зпд.мкр"
            }


            val timeNow = SimpleDateFormat("HH.mm") //получаем дату в простом 24.00 формате
            val timeNowFormated = timeNow.format(Date()) // форматируем полученные данные
            val timeNowDouble = timeNowFormated.toDouble() // приводим к Double




            val one_down_work:MutableList<Double> = mutableListOf(5.40, 6.00,      6.20, 6.35, 6.40, 6.55, 7.05, 7.20, 7.30, 7.40, 7.55, 8.10, 8.20, 8.35, 8.50, 9.00, 9.20, 9.40, 10.00, 10.10, 10.20, 10.30, 10.50, 11.00, 11.10, 11.25, 11.35, 11.45, 12.05, 12.15, 12.25, 12.35, 12.55, 13.15, 13.40, 13.50, 14.10, 14.40, 14.50, 15.00, 15.10, 15.20, 15.35, 15.45, 15.55, 16.05, 16.15, 16.25, 16.50, 17.05, 17.20, 17.25, 17.35, 17.50, 18.05, 18.20, 18.45, 18.55, 19.10, 19.55, 20.05
                ,   20.25, 23.59, 0.00, 6.34)//после пробелов значение мнимое
            val one_up_work:MutableList<Double> = mutableListOf(6.00, 6.20,      6.40, 6.55, 7.05, 7.15, 7.25, 7.40, 7.50, 8.00, 8.20, 8.35, 8.40, 8.55, 9.10, 9.20, 9.40, 10.00, 10.20, 10.30, 10.40, 10.50, 11.10, 11.20, 11.30, 11.45, 11.55, 12.10, 12.25, 12.35, 12.45, 12.55, 13.15, 13.35, 14.00, 14.10, 14.30, 15.00, 15.10, 15.25, 15.30, 15.40, 15.55, 16.05, 16.15, 16.25, 16.35, 16.55, 17.10, 17.25, 17.40, 17.45, 17.55, 18.15, 18.25, 18.40, 19.05, 19.20, 19.30, 20.15, 20.25,   20.45, 23.59, 0.00, 6.54)//последнее значение мнимое
            //val one_down_work:MutableList<Double> = mutableListOf(5.55, 6.15,      6.35, 7.20, 8.20, 10.00, 11.10, 12.25, 13.15, 15.10, 15.55, 16.50, 17.25, 17.50, 18.45, 19.10, 20.05,   20.25, 23.59, 0.00, 6.34)//после пробелов значение мнимое
            //val one_up_work:MutableList<Double> = mutableListOf(6.15, 6.35,      06.55, 7.40, 8.40, 10.20, 11.30,  12.45,  13.35, 15.30, 16.15, 17.10, 17.45, 18.15, 19.05, 19.30, 20.25,   20.45, 23.59, 0.00, 6.54)//последнее значение мнимое
            val one_down_holy:MutableList<Double> = mutableListOf(5.50, 6.10,     6.30, 7.10, 7.30, 7.50, 8.10, 8.40, 9.20, 9.45, 10.00, 10.20, 10.40, 11.00, 11.10, 11.40, 12.25, 12.45, 13.25, 14.05, 14.45, 15.25, 15.45, 16.10, 16.30, 16.50, 17.25,18.05, 18.45,  20.05,   20.25, 23.59, 0.00, 6.29)//последнее значение мнимое
            val one_up_holy:MutableList<Double> = mutableListOf(6.10, 6.30,      6.50, 7.30, 7.50, 8.10, 8.30, 9.00, 9.40, 10.05, 10.20, 10.40, 11.00, 11.20, 11.30, 12.00, 12.45, 13.05, 13.45, 14.25, 15.05, 15.45, 16.10, 16.30, 16.50, 17.10, 17.45, 18.25, 19.05, 20.25,   20.45, 23.59, 0.00, 6.49)//последнее значение мнимое
            //val virtual_revers:MutableList<Double> = mutableListOf(6.35, 6.55,      7.15, 8.00, 9.00, 10.40, 11.50, 13.05, 13.55, 15.50, 16.35, 17.30, 18.05, 18.35, 19.25, 19.50, 20.45,  3.0, 3.0, 3.0, 3.0)
            val virtual_revers:MutableList<Double> = mutableListOf(6.20, 6.40,      7.00, 7.15, 7.25, 7.35, 7.45, 8.00, 8.10, 8.20, 8.40, 8.55, 9.00, 9.15, 9.30, 9.50, 10.00, 10.20, 10.40, 10.50, 11.00, 11.10, 11.30, 11.40, 11.50, 12.05, 12.15, 12.30, 12.45, 12.55, 13.05, 13.15, 13.35, 13.55, 14.20, 14.30, 14.50, 15.20, 15.30, 15.45, 15.50, 16.00, 16.15, 16.25, 16.35, 16.45, 16.55, 17.15, 17.30, 17.45, 18.00, 18.05, 18.15, 18.35, 18.45, 19.00, 19.25, 19.40, 19.50, 20.35, 20.45,  3.0, 3.0, 3.0, 3.0)
            val virtual_revers_2:MutableList<Double> = mutableListOf(6.30, 6.50,     7.10, 7.50, 8.10, 8.30, 8.50, 9.20, 10.00, 10.25, 10.40, 11.00, 11.20, 11.40, 11.50, 12.20, 13.05, 13.25, 14.05, 14.45, 15.25, 16.05, 16.30, 16.50, 17.10, 17.30, 18.05, 18.45, 19.25, 20.45,  3.0, 3.0, 3.0, 3.0)//виртуальный список для выходных
            var beforeXX = 0.0
            var beforeX = 0.0
            var pastX = 0.0
            var indxi = 0
            var indxii = 0
            var revers_time = 0.0
            var revers_time_before = 0.0
            var LenOfList = 0
            var firstRace = 0.0 //значение первого рейса
            var currentTimeList:MutableList<Double> = mutableListOf()






            if (direct == "down" && work_holy == "work") {
                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_3_d_w") //отправляет в аналитику строковое значение
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag3w.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)
                }
                for (i in one_down_work) {
                    if (i > timeNowDouble && timeNowDouble >= one_down_work[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_down_work.size  //количество элементов в коллекции
                        indxi = one_down_work.indexOf(i) - 1
                        indxii = one_down_work.indexOf(i) - 2//вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_down_work
                        beforeX = one_down_work[indxi]
                        beforeXX = one_down_work[indxii]
                        revers_time = one_up_work[indxi]
                        revers_time_before =one_up_work[indxii]
                        firstRace = currentTimeList[0]
                        break
                    } else {
                        currentTimeList = one_down_work
                        continue
                    }
                }
            }else if (direct == "up" && work_holy == "work"){
                /*привязываем к кнопке фрагмент*/

                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_3_u_w")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag3w.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)
                }
                for (i in one_up_work) {
                    if (i > timeNowDouble && timeNowDouble >= one_up_work[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_up_work.size
                        indxi = one_up_work.indexOf(i) - 1
                        indxii = one_up_work.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_up_work
                        beforeX = one_up_work[indxi]
                        beforeXX = one_up_work[indxii]
                        revers_time = virtual_revers[indxi]
                        revers_time_before = virtual_revers[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_up_work
                        continue
                    }
                }
            }else if (direct == "down" && work_holy == "holy"){
                /*привязываем к кнопке фрагмент*/
                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_3_d_h")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag3h.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)
                }
                for (i in one_down_holy) {
                    if (i > timeNowDouble && timeNowDouble >= one_down_holy[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_down_holy.size
                        indxi = one_down_holy.indexOf(i) - 1
                        indxii = one_down_holy.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_down_holy
                        beforeX = one_down_holy[indxi]
                        beforeXX = one_down_holy[indxii]
                        revers_time = one_up_holy[indxi]
                        revers_time_before = one_up_holy[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_down_holy
                        continue
                    }
                }
            }else if(direct == "up" && work_holy == "holy"){
                /*привязываем к кнопке фрагмент*/

                binding.shedule.setOnClickListener {
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_NAME, "shedule_3_u_h")
                    }
                    binding.shedule.animate().apply {
                        duration = 150
                        scaleXBy(0.1f)

                    }.withEndAction(){
                        binding.shedule.animate().apply {
                            duration = 150
                            scaleXBy(-0.1f)
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed({ //запуск отложенной анимации// новый Handler /////////////////////////////////////////////////////////////////////////

                        supportFragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out) // анимация фрагмента при открытии и закрытии
                            .replace(R.id.PlaceHolder, SheduleFrag3h.newInstance())
                            .addToBackStack(null) //возвращает в то же активити, в котором находится фрагмент
                            .commit()
                    }, 400)
                }
                for (i in one_up_holy) {
                    if (i > timeNowDouble && timeNowDouble >= one_up_holy[2]) {               //находим ближайшие временные метки от настоящего времени
                        pastX = i
                        LenOfList = one_up_holy.size
                        indxi = one_up_holy.indexOf(i) - 1
                        indxii = one_up_holy.indexOf(i) - 2 //вычисляем индекс для дважды предыдущий рейс
                        currentTimeList = one_up_holy
                        beforeX = one_up_holy[indxi]
                        beforeXX = one_up_holy[indxii]
                        revers_time = virtual_revers_2[indxi]
                        revers_time_before = virtual_revers_2[indxii]
                        firstRace = currentTimeList[0]
                        break
                    }else {
                        currentTimeList = one_up_holy
                        continue
                    }
                }
            }




            //парсим значения времени

            val revers_time_beforeF = String.format(Locale.CHINA, "%.2f", revers_time_before)
            val revers_timeF = String.format(Locale.CHINA, "%.2f", revers_time)
            val pastF = String.format(Locale.CHINA, "%.2f", pastX)//добавляет хвостовые нули после точки
            val beforeF = String.format(Locale.CHINA, "%.2f", beforeX) //добавляет хвостовые нули после точки
            val beforeXXF = String.format(Locale.CHINA, "%.2f", beforeXX)
            val time_now_F_list: List<String> = timeNowFormated.split(".") //разбиваем значение настоящего времени на часы и минуты и кладем их в список
            val aTNF = time_now_F_list[0] //кладем в переменную значение "часы"
            val bTNF = time_now_F_list[1] //кладем в переменную значение "минуты"
            val pastF_list: List<String> = pastF.split(".") //разбиваем значение "рейс после" на часы и минуты
            val aPastRace = pastF_list[0] //кладем в переменную значение "часы"
            val bPastRace = pastF_list[1] //кладем в переменную значение "минуты"
            val beforeF_list: List<String> = beforeF.split(".") //разбиваем значение "рейс до" на часы и минуты
            val aBeforeRace = beforeF_list[0] //кладем в переменную значение "часы"
            val bBeforeRace = beforeF_list[1] //кладем в переменную значение "минуты"
            val beforeXXF_list: List<String> = beforeXXF.split(".")
            val aBXX = beforeXXF_list[0]
            val bBXX = beforeXXF_list[1]
            val revers_time_list: List<String> = revers_timeF.split(".")
            val aReversRace = revers_time_list[0]
            val bReversRace = revers_time_list[1]
            val revers_time_before_list: List<String> = revers_time_beforeF.split(".")
            val aReversRaceBefore = revers_time_before_list[0]
            val bReversRaceBefore = revers_time_before_list[1]




            //блок определения временных интервалов между рейсами в различных последовательностях
            val timeNowForCompare = LocalTime.of(aTNF.toInt(), bTNF.toInt())//кладем настоящее время для дальнейшего сравнения
            val timeReversRaceBefore = LocalTime.of(aReversRaceBefore.toInt(), bReversRaceBefore.toInt())//кладем время дважды предыдущего обратного рейса
            val timeReverseRace = LocalTime.of(aReversRace.toInt(), bReversRace.toInt())//кладем значение часа и минут рейса из обратного направления для последующего сравнения
            val timeBeforeRace = LocalTime.of(aBeforeRace.toInt(), bBeforeRace.toInt())//кладем значение часа и минут предыдущего рейса для последующего сравнения
            val timeBeforeXX = LocalTime.of(aBXX.toInt(), bBXX.toInt() )//кладем значение часа и минут дважды предыдущего рейса для последующего сравнения
            val timePastRace = LocalTime.of(aPastRace.toInt(), bPastRace.toInt()) // значения часа и минут последующего рейса для дальнейшего сравнения


            val timeStartStop = Duration.between(timeBeforeRace, timeReverseRace)//получаем время продолжительности рейса. сравнивается с виртуальным временем
            val timeBeforeDoubleBefore = Duration.between(timeBeforeXX, timeBeforeRace)//получаем время между рейсом "до" и дважды "до"
            val timeStartStopBefore = Duration.between(timeBeforeXX, timeReversRaceBefore)//получаем время дважды предыдущего рейса старт стоп
            val timeBeforePast = Duration.between(timeBeforeRace, timePastRace) //получаем время между рейсом "до" и рейсом "после"
            val timeBeforeNow = Duration.between(timeBeforeRace, timeNowForCompare)//продолжительность между "до" и "сейчас"
            val timeDoubleBeforeNow = Duration.between(timeBeforeXX, timeNowForCompare)//продолжительность между дважды "до" и "сейчас"
            val timeNowPast = Duration.between(timeNowForCompare, timePastRace)// продолжительность между "сейчас" и рейсом "после"

            var StartStop = timeStartStop.toMinutes().toInt()//в минутах старт стоп
            val BeforeDoubleBefore = timeBeforeDoubleBefore.toMinutes().toInt()//в минутах между дважды "до" и "до"
            val StartStopBefore = timeStartStopBefore.toMinutes().toInt()// в минутах пердыдущий старт стоп
            val BeforePast = timeBeforePast.toMinutes().toInt()//в минутах между рейсом "до" и рейсом "после"
            val differenceBeforeNow = timeBeforeNow.toMinutes().toInt()//в минутах между "до" и "сейчас"
            val differenceBeforeXXNow = timeDoubleBeforeNow.toMinutes().toInt()//в минутах между дважды "до" и "сейчас"
            val differenceNowPast = timeNowPast.toMinutes().toInt()// в минутах между "сейчас" и "после"





/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/

            if (timeNowDouble == 23.59 || timeNowDouble == 0.00){

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                val formatTime = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
                binding.textView5.text = "автобус выйдет на маршрут в $formatTime"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                return
            }

            if (timeNowDouble > 0.00 && timeNowDouble < currentTimeList[2]){

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                val formatTime = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
                binding.textView5.text = "автобус выйдет на маршрут в $formatTime"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                return
            }
/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/




            //параметры анимации
            val widthView2 = binding.imageView2.getDrawable().getIntrinsicWidth()// получаем ширину imageView2 - длинна пути
            val kf: Float = 1.85f // коэффициет соотношения между Dp и Px

            val x = 0f
            val z = ((widthView2 / StartStop) * (differenceBeforeNow)).toFloat()//продолжительность анимации по длинне по y или x. это значение dp, для правильной анимации на всех устройствах нужно в атрибутах выставить выставить px
            val z2 = ((widthView2 / StartStopBefore) * (differenceBeforeXXNow)).toFloat()
            val value_duration = 60000 * 0.05 //продолжительность анимации по времени
            val animator = ValueAnimator.ofFloat(x, z / kf)//значения аниматора представлены переменными
            val animator2 = ValueAnimator.ofFloat(x, z2 / kf)
            animator.interpolator = AccelerateDecelerateInterpolator()      //задает ускорение или замедление анимации или отключает
            animator.duration = value_duration.toLong()    //продолжительность анимации
            animator2.interpolator = AccelerateDecelerateInterpolator()      //задает ускорение или замедление анимации или отключает
            animator2.duration = value_duration.toLong()      //продолжительность анимации
            animator.start()
            animator2.start()

            if (z2 > widthView2 && z < widthView2){                      //определяем видимость или невидимость второго автобуса

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView7.isVisible = false
                binding.numB.isVisible = false
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0){
                    binding.textView5.text ="рейс $beforeF в пути меньше минуты"
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }else{
                    binding.textView5.text ="рейс $beforeF в пути $differenceBeforeNow мин."
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }

            }else if(z > widthView2 && z2 > widthView2){ //при таких условиях автобусов на линии нет

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView.isVisible = false
                binding.textView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView6.isVisible = false
                binding.textView7.isVisible = false
                binding.textView14.isVisible = true
                binding.numA.isVisible = false
                binding.numB.isVisible = false
                binding.textView5.text = "рейс $pastF выйдет на маршрут через $differenceNowPast мин."

            }else {

                params.topMargin = (315*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (315*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = true //определяем видимость первого автобуса и соответствующих текстов
                binding.textView13.isVisible = true
                binding.textView13.text = beforeXXF
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0) {
                    binding.textView6.text = "рейс $beforeXXF в пути $differenceBeforeXXNow мин."
                    binding.textView5.text = "рейс $beforeF в пути меньше минуты"
                    binding.textView7.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                } else {
                    binding.textView6.text = "рейс $beforeXXF в пути $differenceBeforeXXNow мин."
                    binding.textView5.text = "рейс $beforeF в пути $differenceBeforeNow мин"
                    binding.textView7.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                }
            }


            if (timeNowDouble >= currentTimeList[2] && timeNowDouble < currentTimeList[3]){

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton



                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView6.isVisible = true
                binding.textView7.isVisible = false
                binding.numB.isVisible = false
                binding.textView4.text = beforeF
                if (differenceBeforeNow == 0){
                    binding.textView5.text ="рейс $beforeF в пути меньше минуты"
                    binding.textView6.text = "следующий рейс в $pastF, через $differenceNowPast мин."
                }else{
                    binding.textView5.text ="рейс $beforeF в пути $differenceBeforeNow мин"
                    binding.textView6.text ="следующий рейс в $pastF, через $differenceNowPast мин."
                }
            }





            if ((LenOfList - 4) == (indxi + 1)){//последний рейс

                params.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (215*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton

                if (differenceBeforeNow == 0){
                    binding.textView5.text = "рейс $beforeF в пути меньше минуты"
                    binding.textView6.text = "сегодня этот рейс последний"
                }else{
                    binding.textView5.text = "рейс $beforeF в пути ${differenceBeforeNow} мин."
                    binding.textView6.text = "сегодня этот рейс последний"
                }
            }

            val firstOfList = String.format(Locale.CHINA, "%.2f", currentTimeList[2])
            if (timeNowDouble >= currentTimeList[LenOfList - 4] && timeNowDouble < 23.59) {

                params.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.shedule.layoutParams = params     //смещение кнопки shedule в зависимости от условий

                params1.topMargin = (115*destiny).toInt()  // формула перевода px в dpi - (dpi*destiny)
                binding.taxiButton.layoutParams = params1  //смещение кнопки taxiButton

                binding.textView5.text = "движение автобусов на сегодня завершено"
                binding.imageView.isVisible = false
                binding.imageView4.isVisible = false
                binding.textView13.isVisible = false
                binding.textView4.isVisible = false
                binding.textView6.isVisible = false
                binding.textView14.isVisible = true
                binding.textView7.isVisible = false
                binding.numA.isVisible = false
                binding.numB.isVisible = false
            }





            animator.addUpdateListener(object:
                ValueAnimator.AnimatorUpdateListener {  //аниматор для imageView
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    val animatedValue = animation?.animatedValue as Float
                    binding.imageView.translationX = animatedValue
                    binding.textView4.translationX = animatedValue
                    binding.numA.translationX = animatedValue

                }
            })



            animator2.addUpdateListener(object:
                ValueAnimator.AnimatorUpdateListener { //аниматор для imageView4
                override fun onAnimationUpdate(animation: ValueAnimator?) {
                    val animatedValue = animation?.animatedValue as Float
                    binding.imageView4.translationX = animatedValue
                    binding.textView13.translationX = animatedValue
                    binding.numB.translationX = animatedValue



                }
            })
        }
    }


    /*private fun buttonsTaps(){
        binding = ActivityFirst11Binding.inflate(layoutInflater)
        binding.taxiButton.setOnClickListener {
            checkForPermission(android.Manifest.permission.CALL_PHONE, "phone", CALL_PHONE_RQ)
            val intent = Intent(ACTION_CALL, Uri.parse("tel:" + "+79524049959"))
            startActivity(intent)
        }
    }*/



    //проверка наличия разрешений
    private fun checkForPermission(permissions: String, name: String, requestCode: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            when{
                ContextCompat.checkSelfPermission(applicationContext, permissions) == PackageManager.PERMISSION_GRANTED -> {
                    //Toast.makeText(applicationContext, "$name permisson granted", Toast.LENGTH_SHORT).show()
                    firebaseAnalytics = Firebase.analytics //инициализация firebase
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_UP) {                                //
                        param(FirebaseAnalytics.Param.ITEM_NAME, "taxiButton")                                         //
                    }

                    val intent = Intent(ACTION_CALL, Uri.parse("tel:" + "+79508273777"))
                    firebaseAnalytics = Firebase.analytics //инициализация firebase
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_UP) {                                //
                        param(FirebaseAnalytics.Param.ITEM_NAME, "taxiButton")                                         //
                    }

                    startActivity(intent)
                }
                shouldShowRequestPermissionRationale(permissions) -> showDialog(permissions, name, requestCode)
                else -> ActivityCompat.requestPermissions(this, arrayOf(permissions), requestCode)

            }
    }



    //запрос разрешений
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fun innerChek(name: String){
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Разрешите приложению $name совершать вызовы", Toast.LENGTH_SHORT).show()
            } else {
                //Toast.makeText(applicationContext, "$name permission granted", Toast.LENGTH_SHORT).show()
                val intent = Intent(ACTION_CALL, Uri.parse("tel:" + "+79508273777"))
                startActivity(intent)

            }
        }
        when (requestCode){
            CALL_PHONE_RQ -> innerChek("GetBus")
        }
    }
    //определение текстов в диалогах
    private fun showDialog(permission: String, name: String, requestCode: Int){
        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("Для работы этой функции требуется разрешение для совершения звонков")
            setTitle("Требуется разрешение")
            setPositiveButton("OK"){dialog, which ->
                ActivityCompat.requestPermissions(this@First1_1, arrayOf(permission), requestCode)

            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}



