package com.anthony.gradientplayground

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ANIMATION_RUNNING_BUNDLE_KEY = "animationRunning"
        const val GRADIENT_FADE_MS_LENGTH = 2000
    }

    private val animationDrawable: AnimationDrawable by lazy {
        main_gradient_view.background as AnimationDrawable
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupGradientAnimation()
    }


    override fun onSaveInstanceState(state: Bundle?) {
        super.onSaveInstanceState(state)
        state?.putBoolean(ANIMATION_RUNNING_BUNDLE_KEY, animationDrawable.isRunning)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let {
            if (it.getBoolean(ANIMATION_RUNNING_BUNDLE_KEY, false)) {
                animationDrawable.start()
                main_animate_button.text = getString(R.string.animate_button_stop_label)
            }
        }
    }

    private fun setupGradientAnimation() {
        animationDrawable.setEnterFadeDuration(GRADIENT_FADE_MS_LENGTH)
        animationDrawable.setExitFadeDuration(GRADIENT_FADE_MS_LENGTH)
        main_animate_button.setOnClickListener {
            if (animationDrawable.isRunning) {
                animationDrawable.stop()
                main_animate_button.text = getString(R.string.animate_button_start_label)
            }
            else {
                animationDrawable.start()
                main_animate_button.text = getString(R.string.animate_button_stop_label)
            }
        }
    }
}
