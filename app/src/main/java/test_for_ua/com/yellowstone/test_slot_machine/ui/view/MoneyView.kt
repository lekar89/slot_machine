package test_for_ua.com.yellowstone.test_slot_machine.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import kotlinx.android.synthetic.main.view_money.view.*
import test_for_ua.com.yellowstone.test_slot_machine.R

class MoneyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {
    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.view_money, this)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        try {
            val text = ta.getString(R.styleable.CustomView_money_value)
            val drawableId = ta.getResourceId(R.styleable.CustomView_image, 0)
            if (drawableId != 0) {
                val drawable = AppCompatResources.getDrawable(context, drawableId)
                iv_type.setImageDrawable(drawable)
            }
            tv_sum.text = text
        } finally {
            ta.recycle()
        }
    }
     fun setSum(sum:Int){
        tv_sum.text = sum.toString()
    }

    fun getSum(): Int {
         return tv_sum.text.toString().toInt()
    }
}