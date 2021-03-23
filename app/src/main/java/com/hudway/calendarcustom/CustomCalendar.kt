package com.hudway.calendarcustom

import android.R.attr.spacing
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children



class CustomComponent : ViewGroup {
    var sizeVertical = 7
    var sizeHorizontal = 8
    var widthCell = 150f
    var heightCell = 150f
    var paddingCells = 1f
    var beginCell = 0;
    constructor(
        context: Context,
        attrs: AttributeSet? = null,

        ) : super(context, attrs) {

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CellLayout, 0, 0
        )

        sizeVertical = typedArray.getInt(R.styleable.CellLayout_layout_cells_vertical, 5)
        sizeHorizontal = typedArray.getInt(R.styleable.CellLayout_layout_cells_horizontal, 5)
        paddingCells = typedArray.getDimension(R.styleable.CellLayout_spacing, 0f)
        beginCell = typedArray.getInt(R.styleable.CellLayout_layout_begin_cell, 0)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        widthCell = ((measuredWidth - paddingCells*2).toFloat() / sizeHorizontal)
        heightCell = ((measuredHeight - paddingCells*2).toFloat()/sizeVertical )

        for (child in children){
            var layoutparams = child.layoutParams
            layoutparams.height = heightCell.toInt()
            layoutparams.width = widthCell.toInt()
            child.layoutParams = layoutParams
        }

        setMeasuredDimension(width,height)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        val childCount = childCount
        var horizontCount = 0
        var child: View
        if(beginCell==0){
            horizontCount=-1
        }
        for ( i in 0 until childCount) {
            var verticalCounter = i + beginCell
            if(verticalCounter>=sizeHorizontal*sizeVertical){
                break
            }
            child = getChildAt(i)
            if (verticalCounter % sizeHorizontal==0){
                horizontCount++
            }
            val top = (((horizontCount % sizeVertical) * heightCell).toInt() +  paddingCells).toInt()
            val left = (((verticalCounter % sizeHorizontal) * widthCell).toInt() +  paddingCells).toInt()
            val right = ((((verticalCounter % sizeHorizontal) + 1) * widthCell).toInt()  - paddingCells).toInt()
            val bottom = ((((horizontCount % sizeVertical) + 1) * heightCell).toInt() - paddingCells).toInt()
            child.layout(left, top, right, bottom)

            Log.v("layout", "verticalCounter %= " + (verticalCounter % sizeHorizontal) + " horiontalCount% = "+horizontCount + " child = "+(child as Button).text + " cord ("+left + ";" + top+";"+right+";"+bottom+")")

        }


    }

}