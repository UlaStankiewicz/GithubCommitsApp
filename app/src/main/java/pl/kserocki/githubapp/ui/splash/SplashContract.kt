package pl.kserocki.githubapp.ui.splash

import pl.kserocki.githubapp.ui.base.BaseContract

interface SplashContract : BaseContract {

    interface View : BaseContract.View {
        fun goToSearchActivity()
    }

    abstract class ViewModel : BaseContract.ViewModel()

}