package com.thinhlh.androidbase.utils.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlin.system.exitProcess

/**
 * Created by thinhlh on 09/02/2022.
 * Copyright (c). All rights reserved
 */
object SystemHelper {

    /**
     * Hide status bar & navigation bar
     */
    fun hideSystemUI(window: Window) {
        window.run {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            } else {
                setDecorFitsSystemWindows(false)
                insetsController?.let { controller ->
                    controller.hide(
                        WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
                    )
                    controller.systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            }
        }
    }

    /**
     * Show or Hide keyboard
     */
    fun toggleSoftInput(context: Context, show: Boolean) {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) {
            inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
            (context as Activity).window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                        or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            )
            return
        }

        (context as Activity).window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                    or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )
        val view = context.currentFocus
        if (view != null) {
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
        return
    }

    /**
     * Show or Hide keyboard
     */
    fun toggleSoftInput(editText: EditText, show: Boolean) {
        (editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .apply {
                if (show) {
                    toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                } else {
                    hideSoftInputFromWindow(editText.windowToken, 0)
                }
            }
    }

    /**
     * Get display size
     */
    fun getDisplaySize(displayMetrics: DisplayMetrics): Point {
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels
        return Point(width, height)
    }

    /**
     * Restart app
     */
    fun <C> restartApp(context: Context?, mainClass: Class<C>) {
        context?.run {
            startActivity(Intent(this, mainClass).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
            exitProcess(0)
        }
    }
}