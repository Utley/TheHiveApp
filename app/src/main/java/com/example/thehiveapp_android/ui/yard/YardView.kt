package com.example.thehiveapp_android.ui.yard

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.thehiveapp_android.R
import org.jetbrains.annotations.TestOnly

/**
 * More visual stuffs?
 *
 * @author ???
 */
class YardView : View {

    private var _exampleString: String? = "something" // TODO: use a default from R.string...
    private var _exampleColor: Int = Color.RED // TODO: use a default from R.color...
    private var _exampleDimension: Float = 0f // TODO: use a default from R.dimen...

    private var textPaint: TextPaint? = null
    private var textWidth: Float = 0f
    private var textHeight: Float = 0f

    /**
     * The text to draw
     */
    var exampleString: String?
        get() = _exampleString
        set(value) {
            _exampleString = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * The font color
     */
    var exampleColor: Int
        get() = _exampleColor
        set(value) {
            _exampleColor = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * In the example view, this dimension is the font size.
     */
    var exampleDimension: Float
        get() = _exampleDimension
        set(value) {
            _exampleDimension = value
            invalidateTextPaintAndMeasurements()
        }

    /**
     * In the example view, this drawable is drawn above the text.
     */
    var exampleDrawable: Drawable? = null

    /**
     *
     */
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    /**
     *
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    /**
     *
     */
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.YardView, defStyle, 0
        )

//        _exampleString = a.getString(
//            R.styleable.HiveDiagramView_exampleString
//        )
        _exampleColor = a.getColor(
            R.styleable.YardView_exampleColor,
            exampleColor
        )
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        _exampleDimension = a.getDimension(
            R.styleable.YardView_exampleDimension,
            exampleDimension
        )

        if (a.hasValue(R.styleable.YardView_exampleDrawable)) {
            exampleDrawable = a.getDrawable(
                R.styleable.YardView_exampleDrawable
            )
            exampleDrawable?.callback = this
        }

        a.recycle()

        // Set up a default TextPaint object
        textPaint = TextPaint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            textAlign = Paint.Align.LEFT
        }

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements()
    }

    private fun invalidateTextPaintAndMeasurements() {
        textPaint?.let {
            it.textSize = exampleDimension
            it.color = exampleColor
            textWidth = it.measureText(exampleString)
            textHeight = it.fontMetrics.bottom
        }
    }

    /**
     * Test function
     */
    fun drawTestHives(canvas: Canvas) {
        val hive_width: Float = 150F
        val hive_height: Float = 150F

        var hiveColor:Int = 0xFF_FF_00_FF.toInt()
        var hivePaint:Paint = Paint(hiveColor)

        canvas.apply {
            drawRect(0F,0F, hive_width, hive_height, hivePaint)
        }

    }

    /**
     * Test function
     */
    fun testDraw(canvas: Canvas) {
        drawTestHives(canvas)


    }

    /**
     * Specifies the object's behavior when asked to draw itself; renders the object on the given
     * canvas.
     *
     * @param canvas The canvas to draw ourselves on
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        testDraw(canvas)

        // TODO: consider storing these as member variables to reduce allocations per draw cycle.
        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom

        val testPaint: Paint = Paint()

        exampleString?.let {
            // Draw the text.
            canvas.drawText(
                it,
                paddingLeft + (contentWidth - textWidth) / 2,
                paddingTop + (contentHeight + textHeight) / 2,
                testPaint
            )
        }

        // Draw the example drawable on top of the text.
        exampleDrawable?.let {
            it.setBounds(
                paddingLeft, paddingTop,
                paddingLeft + contentWidth, paddingTop + contentHeight
            )
            it.draw(canvas)
        }
    }
}