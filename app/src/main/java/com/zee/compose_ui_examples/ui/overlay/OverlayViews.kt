package com.zee.compose_ui_examples.ui.overlay

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.zee.compose_ui_examples.R

class OverlayViews(private val context: Context) {

    private var _root: View? = null
    private lateinit var closeBtn: ImageView
    private lateinit var homeBtn: ImageView
    private lateinit var maxMinBtn: ImageView
    private val root: View get() = _root!!
    private var isMaximize = false
    private var _overlayWindowListener: OverlayWindowListener? = null
    private val manager get() = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager


    companion object {
        private const val TAG = "OverlayViews"
    }

    init {
        initUi()
    }

    private fun initUi() {

        _root = LayoutInflater.from(context).inflate(R.layout.overlay_layout, null, false)

        root.apply {

            addRemoveFullScreenView(false)

            homeBtn = findViewById(R.id.home)
            closeBtn = findViewById(R.id.close)
            maxMinBtn = findViewById(R.id.maxMinBtn)

            homeBtn.setOnClickListener {
                _overlayWindowListener?.returnToHome()
            }

            closeBtn.setOnClickListener {
                closeWindow()
                _overlayWindowListener?.onClose()
            }

            maxMinBtn.setOnClickListener {
                isMaximize = !isMaximize
                val drawableId = if (isMaximize) R.drawable.ic_maximize else R.drawable.ic_minimize
                maxMinBtn.setImageDrawable(context.getDrawable(drawableId))
                addRemoveFullScreenView(isMaximize)
            }
        }
        println("zeeshan overlay started ....")
    }

    private fun addRemoveFullScreenView(maximize: Boolean) {
        if (_root != null && _root?.isAttachedToWindow == true)
            manager.removeView(_root)

        var width = WindowManager.LayoutParams.MATCH_PARENT

        if (!maximize) {
            width = WindowManager.LayoutParams.WRAP_CONTENT
        }

        val heightInPx = if (maximize) context.resources.displayMetrics.heightPixels / 2 else WindowManager.LayoutParams.WRAP_CONTENT

        val mParams: WindowManager.LayoutParams = WindowManager.LayoutParams(
            width, heightInPx,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSPARENT,
        )

        mParams.gravity = Gravity.START or Gravity.TOP

        manager.addView(root, mParams)
    }

    private fun closeWindow() {
        manager.removeView(_root)
        _root?.invalidate()
        (_root as ViewGroup?)?.removeAllViews()
    }


    fun setWindowCloseListener(overlayWindowListener: OverlayWindowListener) {
        _overlayWindowListener = overlayWindowListener
    }


}