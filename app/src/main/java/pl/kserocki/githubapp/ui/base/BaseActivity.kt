package pl.kserocki.githubapp.ui.base

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import pl.kserocki.githubapp.R

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
    }

    abstract fun initializeUI()

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.fragment_fade_enter,
            R.anim.fragment_fade_exit
        )
    }

    fun myStartActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
        overridePendingTransition(
            R.anim.fragment_fade_enter,
            R.anim.fragment_fade_exit
        )
    }

}