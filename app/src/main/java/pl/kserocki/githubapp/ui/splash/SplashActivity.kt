package pl.kserocki.githubapp.ui.splash

import android.view.View
import pl.kserocki.githubapp.R
import pl.kserocki.githubapp.utils.Extensions.clickAnimation
import pl.kserocki.githubapp.ui.base.BaseActivity
import pl.kserocki.githubapp.ui.details.SearchActivity
import pl.kserocki.githubapp.utils.ClickCallback

class SplashActivity : BaseActivity(R.layout.activity_splash), SplashContract.View {

    fun onContinueButtonClick(view: View) {
        view.clickAnimation(object : ClickCallback {
            override fun onClickFinish() {
                goToSearchActivity()
            }
        })
    }

    override fun goToSearchActivity() {
        myStartActivity(SearchActivity::class.java)
    }

}