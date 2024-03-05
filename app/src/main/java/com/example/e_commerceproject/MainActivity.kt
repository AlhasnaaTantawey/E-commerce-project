package com.example.e_commerceproject

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.e_commerceproject.utils.AddToCartException
import com.example.e_commerceproject.utils.CrashlyticsUtils
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<TextView>(R.id.textview).setOnClickListener {
//            Log.d("MainActivity","crash in onclicklistener ")
//            lifecycleScope.launch(Main) {
//              CrashlyticsUtils.sendLogToCrashlytics("crash button clicked ",
//                  "button", "clicked","crash")
//                CrashlyticsUtils.sendCustomLogToCrashlytics<AddToCartException>("crash button clicked",
//                    Pair(CrashlyticsUtils.ADD_TOCART_KEY,"add to cart button clicked "))
//            }
//        }
    }

private  fun initSplashScreen(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        installSplashScreen()
        // Add a callback that's called when the splash screen is animating to the
        // app content.
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation.
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView, View.TRANSLATION_Y, 0f, -splashScreenView.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 1000L

            // Call SplashScreenView.remove at the end of your custom animation.
            slideUp.doOnEnd { splashScreenView.remove() }

            // Run your animation.
            slideUp.start()
        }
    } else {
        setTheme(R.style.Theme_ECommerceProject)
    }
}
}