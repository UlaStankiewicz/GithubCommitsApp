package pl.kserocki.githubapp.ui.repo

import pl.kserocki.githubapp.ui.base.BaseContract

interface RepoContract : BaseContract {

    interface View : BaseContract.View {
        fun getRepositoryOwner(): String
        fun getRepositoryName(): String
        fun getRepositoryId(): Int
    }

    abstract class ViewModel : BaseContract.ViewModel() {
        abstract fun setOwnerAndRepositoryName(ownerAndRepositoryName: String)
    }

}