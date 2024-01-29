package com.example.dogtranslate.listener

import android.os.SystemClock
import android.view.View

abstract class OnSingleClickListener : View.OnClickListener {
    companion object {
        const val MIN_CLICK_INTERVAL = 500L
    }

    var mLastClickTime = 0L

    abstract fun onSingleClick(view: View)

    override fun onClick(v: View?) {
        val currentClickTime = SystemClock.elapsedRealtime()
        val elapsedTime = currentClickTime - mLastClickTime
        mLastClickTime = currentClickTime

        if (elapsedTime <= MIN_CLICK_INTERVAL) return

        onSingleClick(v!!)
    }
}