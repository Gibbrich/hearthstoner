package com.example.hearthstoner.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.animation.doOnEnd
import com.example.hearthstoner.R
import com.google.android.material.math.MathUtils
import kotlin.math.cos
import kotlin.math.sin

class Loader(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var dotOffsetFactor = 0f
    private var radius: Float = 0f
    private var angle = 0.0f

    private val paint: Paint

    private val animator: AnimatorSet

    init {
        context.theme
            .obtainStyledAttributes(attrs, R.styleable.Loader, 0, 0)
            .apply {
                try {
                    val color = getColor(
                        R.styleable.Loader_color,
                        androidx.appcompat.R.attr.colorPrimary
                    )

                    paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        style = Paint.Style.FILL
                        this.color = color
                    }
                } finally {
                    recycle()
                }
            }

        val rotateAnimator = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 1000
            addUpdateListener {
                angle = it.animatedValue as Float
                invalidate()
            }
        }

        val forwardOffsetAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            startDelay = 300
            duration = 500
            addUpdateListener {
                dotOffsetFactor = it.animatedValue as Float
                invalidate()
            }
        }

        val backwardOffsetAnimator = ValueAnimator.ofFloat(1f, 0f).apply {
            duration = 500
            addUpdateListener {
                dotOffsetFactor = it.animatedValue as Float
                invalidate()
            }
        }

        animator = AnimatorSet().apply {
            doOnEnd(Animator::start)
            playSequentially(forwardOffsetAnimator, rotateAnimator, backwardOffsetAnimator)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.pause()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = w / 4f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Try for a width based on our minimum
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)

        val minh = paddingTop + paddingBottom + suggestedMinimumHeight
        val h: Int = resolveSizeAndState(minh, heightMeasureSpec, 0)

        setMeasuredDimension(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            var angleOffset = 45.0
            for (i in 0..3) {
                val angleRadians = Math.toRadians(angle.toDouble() + angleOffset)
                val factor = MathUtils.lerp(radius / 4f, radius, dotOffsetFactor)
                val x = cos(angleRadians).toFloat() * factor
                val y = sin(angleRadians).toFloat() * factor
                val offsetX = width / 2.0f
                val offsetY = height / 2.0f

                drawCircle(x + offsetX, y + offsetY, 10f, paint)
                angleOffset += 90
            }
        }
    }
}