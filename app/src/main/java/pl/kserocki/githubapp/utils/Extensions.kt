package pl.kserocki.githubapp.utils

import android.view.View

fun View.clickAnimation(clickCallback: ClickCallback) {
    this.animate().apply {
        duration = 100
        scaleXBy(.2f)
        scaleYBy(.2f)
    }.withEndAction {
        this.animate().apply {
            duration = 100
            scaleXBy(-.2f)
            scaleYBy(-.2f)
        }.withEndAction {
            clickCallback.onClickFinish()
        }
    }.start()
}

fun View.clickAnimationReverse(clickCallback: ClickCallback) {
    this.animate().apply {
        duration = 100
        scaleXBy(-.2f)
        scaleYBy(-.2f)
    }.withEndAction {
        this.animate().apply {
            duration = 100
            scaleXBy(.2f)
            scaleYBy(.2f)
        }.withEndAction {
            clickCallback.onClickFinish()
        }
    }.start()
}

fun View.shakeAnimation() {
    this.animate().apply {
        duration = 50
        rotation(5f)
    }.withEndAction {
        this.animate().apply {
            duration = 50
            rotation(-5f)
        }.withEndAction {
            this.animate().apply {
                duration = 50
                rotation(5f)
            }.withEndAction {
                this.animate().apply {
                    duration = 50
                    rotation(0f)
                }
            }
        }
    }
}