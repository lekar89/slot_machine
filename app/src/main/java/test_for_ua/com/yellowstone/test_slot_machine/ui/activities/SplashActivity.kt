package test_for_ua.com.yellowstone.test_slot_machine.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import test_for_ua.com.yellowstone.test_slot_machine.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        iv_logo.startAnimation(createAnimation())
    }

    private fun createAnimation(): Animation {
        val animation = AnimationUtils.loadAnimation(this, R.anim.logo_anim)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                gotoMainScreen()
            }
        })
        return animation
    }

    private fun gotoMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}