package pl.kserocki.githubapp.ui.details

import pl.kserocki.githubapp.ui.base.BaseContract

interface SearchContract : BaseContract {

    interface View : BaseContract.View

    abstract class ViewModel : BaseContract.ViewModel()

}