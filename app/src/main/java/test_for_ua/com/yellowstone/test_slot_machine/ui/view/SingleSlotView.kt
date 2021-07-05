package test_for_ua.com.yellowstone.test_slot_machine.ui.view

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import kotlinx.android.synthetic.main.view_scrolling.view.*
import test_for_ua.com.yellowstone.test_slot_machine.R
import test_for_ua.com.yellowstone.test_slot_machine.ui.interfaces.ISingleSlotResult
import kotlin.random.Random

class SingleSlotView : FrameLayout {
    internal lateinit var result: ISingleSlotResult
    private lateinit var list: MutableList<Int>
    internal var oldValue = 0

    val value: Int
        get() = Integer.parseInt(nextImage.tag.toString())

    fun setEventEnd(result: ISingleSlotResult) {
        this.result = result
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        init(context)
    }

    private fun init(context: Context) {
        list = mutableListOf(
            R.drawable.item0,
            R.drawable.item1,
            R.drawable.item2,
            R.drawable.item3,
            R.drawable.item4,
            R.drawable.item5,
            R.drawable.item6
        )
        LayoutInflater.from(context).inflate(R.layout.view_scrolling, this)
        nextImage.translationY = height.toFloat()
    }

    fun setValueRandom(imageNumber: Int) {
        val numRotate = Random.nextInt(6, 15)
        currentImage.visibility = VISIBLE
        currentImage.animate()
            .translationY((-height).toFloat())
            .setDuration(150L).start()

        nextImage.translationY = nextImage.height.toFloat()

        nextImage.animate().translationY(0f).setDuration(150L)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator?) {
                    setImage(currentImage, oldValue % list.size)
                    currentImage.translationY = 0f
                    if (oldValue < numRotate) {
                        setValueRandom(imageNumber)
                        oldValue++
                    } else {
                        oldValue = 0
                        setImage(nextImage, imageNumber)
                        result.eventEnd(imageNumber % list.size)
                        currentImage.visibility = INVISIBLE
                    }
                }
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            }).start()
    }

    private fun setImage(currentImage: ImageView?, i: Int) {
        currentImage?.setImageResource(list[i])
        currentImage!!.tag = i
        nextImage!!.tag = i
    }
}