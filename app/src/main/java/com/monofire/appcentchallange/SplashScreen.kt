package com.monofire.appcentchallange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import androidx.constraintlayout.motion.widget.MotionLayout
import com.monofire.appcentchallange.ui.LoginActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
     
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val layout = findViewById<MotionLayout>(R.id.mmLayout)
        layout.transitionToEnd()



        object : CountDownTimer(2300, 1000) {
            override fun onFinish() {
                val intent = Intent(this@SplashScreen, LoginActivity::class.java)
                startActivity(intent)
                this@SplashScreen.finish()
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }
}