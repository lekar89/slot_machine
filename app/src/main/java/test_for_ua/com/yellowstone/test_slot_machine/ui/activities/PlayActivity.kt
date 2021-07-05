package test_for_ua.com.yellowstone.test_slot_machine.ui.activities

import android.R.attr.data
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.include_bottom_buttons.*
import test_for_ua.com.yellowstone.test_slot_machine.R
import test_for_ua.com.yellowstone.test_slot_machine.ui.interfaces.ISlotsResult


class PlayActivity : BaseActivity(), ISlotsResult {
    private val betList = mutableListOf(10, 20, 30)
    private var credit = 400
    private var win = 0
    private var currentBatIndex = 0
        set(value) {
            field = if (currentBatIndex == 2) 0 else value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        setInitData()
        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        btn_spin.setOnClickListener {
            if (credit - betList[currentBatIndex] >= 0) {
                slot_view.spin()
            } else {
                Toast.makeText(this, "IMPOSSIBLE, NO CREDITS", Toast.LENGTH_SHORT).show()
            }
        }
        btn_bet_one.setOnClickListener { mv_bet.setSum(betList[++currentBatIndex]) }
        btn_bet_max.setOnClickListener {
            if (currentBatIndex != 2) {
                currentBatIndex = 2
                mv_bet.setSum(betList[currentBatIndex])
            }
        }
    }

    private fun setInitData() {
        mv_bet.setSum(betList[currentBatIndex])
        mv_credit.setSum(credit)
        vm_win.setSum(win)
        slot_view.setSpinEnd(this)
    }

    override fun spinEnded(result: Int) {
        when (result) {
            1 -> {
                credit -= betList[currentBatIndex]
                mv_credit.setSum(credit)
            }
            2 -> {
                win += betList[currentBatIndex]
                vm_win.setSum(win)
            }
            3 -> {
                win += (betList[currentBatIndex] * 10)
                vm_win.setSum(win)
            }

        }
    }

}