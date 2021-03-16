package com.sushant.atosapp.views.base

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

open class CustomTextInputLayout : TextInputLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, 0) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        //This app not having more functionality but in the case we require lot of view
        //end some of them /all of them required some common functionality
        //then this class give me flexibility to adapt
    }
}