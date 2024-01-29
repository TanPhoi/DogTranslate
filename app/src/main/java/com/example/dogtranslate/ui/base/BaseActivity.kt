package com.example.dogtranslate.ui.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dogtranslate.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, M : BaseViewModel> : DaggerAppCompatActivity() {

    private var currentApiVersion = 0
    protected lateinit var binding: T
    protected lateinit var viewModel: M
    protected var dialog: AlertDialog? = null

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewModelClass(): Class<M>

    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        currentApiVersion = Build.VERSION.SDK_INT
        val flags = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = flags
            val decorView = window.decorView
            decorView
                .setOnSystemUiVisibilityChangeListener { visibility ->
                    if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                        decorView.systemUiVisibility = flags
                    }
                }
        }

        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, layoutRes());
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]
        initView()
        createDialog()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun createDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.layout_loading, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        dialog = dialogBuilder.create()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
    }

    fun showLoading() {
        dialog?.show()
    }

    fun hiddenLoading() {
        dialog?.dismiss()
    }

    protected fun <T> observeLiveData(liveData: LiveData<T>, observer: Observer<T>) {
        viewModel.observeLiveData(liveData, observer)
    }

    protected fun <T> removeLiveDataObserver(liveData: LiveData<T>) {
        viewModel.removeLiveDataObserver(liveData)
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }
}
