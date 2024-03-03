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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<TextView>(R.id.textview).setOnClickListener {
//            Log.d("MainActivity","crash in onclicklistener ")
//            throw RuntimeException("Test Crash") // Force a crash
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