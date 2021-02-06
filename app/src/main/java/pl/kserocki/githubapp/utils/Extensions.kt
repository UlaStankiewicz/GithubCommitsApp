package pl.kserocki.githubapp.utils

import android.view.View

object Extensions {

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

}