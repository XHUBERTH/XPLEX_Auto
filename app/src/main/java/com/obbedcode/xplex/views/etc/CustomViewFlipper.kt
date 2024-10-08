package com.obbedcode.xplex.views.etc

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ViewFlipper
import androidx.fragment.app.findFragment
import androidx.lifecycle.lifecycleScope
import com.obbedcode.xplex.views.fragment.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * From LibChecker
 */
class CustomViewFlipper : ViewFlipper {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var mOnDisplayedChildChangedListener: OnDisplayedChildChangedListener? = null

    override fun setDisplayedChild(whichChild: Int) {
        super.setDisplayedChild(whichChild)
        findFragment<BaseFragment<*>>().lifecycleScope.launch(Dispatchers.IO) {
            delay(250)
            withContext(Dispatchers.Main) {
                mOnDisplayedChildChangedListener?.onChanged(whichChild)
            }
        }
    }

    fun getOnDisplayedChildChangedListener() = mOnDisplayedChildChangedListener

    fun setOnDisplayedChildChangedListener(listener: OnDisplayedChildChangedListener) {
        mOnDisplayedChildChangedListener = listener
    }

    fun setOnDisplayedChildChangedListener(onChanged: OnDisplayedChildChangedListener.() -> Unit) {
        mOnDisplayedChildChangedListener = object : OnDisplayedChildChangedListener {
            override fun onChanged(whichChild: Int) {
                onChanged()
            }
        }
    }

    interface OnDisplayedChildChangedListener {
        fun onChanged(whichChild: Int)
    }
}