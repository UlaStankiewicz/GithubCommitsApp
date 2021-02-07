package pl.kserocki.githubapp.ui.search

import pl.kserocki.githubapp.db.entities.GithubRepo
import pl.kserocki.githubapp.models.MyResource
import pl.kserocki.githubapp.ui.base.BaseContract

interface SearchContract : BaseContract {

    interface View : BaseContract.View {
        fun goToRepoActivity(owner: String, repositoryName: String, repositoryId: Int)
        fun onNewRepositoryFound(githubRepo: MyResource<GithubRepo>)
    }

    abstract class ViewModel : BaseContract.ViewModel()

}