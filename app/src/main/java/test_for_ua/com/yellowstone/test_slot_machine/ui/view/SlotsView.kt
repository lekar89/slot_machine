package test_for_ua.com.yellowstone.test_slot_machine.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_slots.view.*
import test_for_ua.com.yellowstone.test_slot_machine.R
import test_for_ua.com.yellowstone.test_slot_machine.ui.interfaces.ISingleSlotResult
import test_for_ua.com.yellowstone.test_slot_machine.ui.interfaces.ISlotsResult
import kotlin.random.Random


class SlotsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    LinearLayout(context, attrs, defStyleAttr), ISingleSlotResult {
    lateinit var spinResult: ISlotsResult
    internal var count_down = 0

    init {
        init(attrs)
    }

    fun setSpinEnd(result: ISlotsResult) {
        this.spinResult = result
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.view_slots, this)
        iv_first_slot.setEventEnd(this)
        iv_second_slot.setEventEnd(this)
        iv_third_slot.setEventEnd(this)
    }

    fun spin() {
        iv_first_slot.setValueRandom(Random.nextInt(7))
        iv_second_slot.setValueRandom(Random.nextInt(7))
        iv_third_slot.setValueRandom(Random.nextInt(7))

    }

    override fun eventEnd(result: Int) {
        if (count_down < 2) {
            count_down++
            return
        }
        count_down = 0
        val res =
            if (iv_first_slot.value == iv_second_slot.value && iv_second_slot.value == iv_third_slot.value) {
                3
            } else if (iv_first_slot.value == iv_second_slot.value || iv_second_slot.value == iv_third_slot.value || iv_first_slot.value == iv_third_slot.value) {
                2
            } else {
                1
            }
        spinResult.spinEnded(res)
    }


}