package pl.kserocki.githubapp.ui.splash

import android.view.View
import pl.kserocki.githubapp.R
import pl.kserocki.githubapp.ui.base.BaseActivity
import pl.kserocki.githubapp.ui.search.SearchActivity
import pl.kserocki.githubapp.utils.ClickCallback
import pl.kserocki.githubapp.utils.clickAnimation

class SplashActivity : BaseActivity(), SplashContract.View {

    override fun initializeUI() {
        setContentView(R.layout.activity_splash)
    }

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