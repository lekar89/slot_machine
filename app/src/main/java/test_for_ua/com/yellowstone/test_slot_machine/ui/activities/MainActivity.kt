package test_for_ua.com.yellowstone.test_slot_machine.ui.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import test_for_ua.com.yellowstone.test_slot_machine.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleOrientation(resources.configuration.orientation)
        btn_play.setOnClickListener { gotoPlayScreen() }
        btn_exit.setOnClickListener { exitApp() }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        handleOrientation(newConfig.orientation)
    }

    private fun gotoPlayScreen() {
        startActivity(Intent(this, PlayActivity::class.java))
        finish()
    }

    private fun exitApp() {
        finish()
    }

    private fun handleOrientation(orientation: Int) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tv_turn_device_text.visibility = View.INVISIBLE
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            tv_turn_device_text.visibility = View.VISIBLE
        }
    }

}