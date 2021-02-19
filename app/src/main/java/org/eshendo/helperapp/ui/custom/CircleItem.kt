package org.eshendo.helperapp.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import org.eshendo.helperapp.R

class CircleItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var imageView: ImageView
    private lateinit var root: ConstraintLayout
    private lateinit var textView: TextView

    private var image: Int = -1
    private var color: Int = -1
    private var text: String? = ""

    init{
        inflate(context, R.layout.circle_item, this)
        findViews()
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleItem,
            0, 0
        ).apply {
            try{
                image = getResourceId(R.styleable.CircleItem_image, -1)
                color = getColor(R.styleable.CircleItem_bgColor, -1)
                text  = getString(R.styleable.CircleItem_text)
            }finally {
                initView()
                recycle()
            }
        }
    }

    private fun findViews(){
        root = findViewById(R.id.root)
        imageView = findViewById(R.id.image)
        textView = findViewById(R.id.text)
    }

    private fun initView(){
        root.backgroundTintList = ColorStateList.valueOf(color)
        textView.text = text
        imageView.setImageResource(image)
    }
}