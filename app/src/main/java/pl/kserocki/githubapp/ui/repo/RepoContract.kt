package pl.kserocki.githubapp.ui.repo

import pl.kserocki.githubapp.ui.base.BaseContract

interface RepoContract : BaseContract {

    interface View : BaseContract.View

    abstract class ViewModel : BaseContract.ViewModel()

}