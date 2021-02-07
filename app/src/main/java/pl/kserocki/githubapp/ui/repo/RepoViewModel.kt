package pl.kserocki.githubapp.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import pl.kserocki.githubapp.db.entities.GithubCommit
import pl.kserocki.githubapp.db.entities.GithubRepo
import pl.kserocki.githubapp.db.repository.GithubCommitRepository
import pl.kserocki.githubapp.db.repository.GithubRepoRepository
import pl.kserocki.githubapp.models.EmptyLiveData
import pl.kserocki.githubapp.models.MyResource
import javax.inject.Inject

class RepoViewModel @Inject
constructor(
    private val githubRepoRepository: GithubRepoRepository,
    private val githubCommitRepository: GithubCommitRepository
) : RepoContract.ViewModel() {

    private val ownerAndRepositoryName: MutableLiveData<String> = MutableLiveData()
    private val githubRepoLiveData: LiveData<MyResource<GithubRepo>>

    val githubCommitsLiveData: LiveData<MyResource<List<GithubCommit>>>

    init {
        githubRepoLiveData = ownerAndRepositoryName.switchMap {
            ownerAndRepositoryName.value?.let { value ->
                githubRepoRepository.loadRepository(
                    value.split("/")[0],
                    value.split("/")[1]
                )
            } ?: EmptyLiveData.create()
        }

        githubCommitsLiveData = ownerAndRepositoryName.switchMap {
            ownerAndRepositoryName.value?.split("/")!![1]
            ownerAndRepositoryName.value?.let { value ->
                githubCommitRepository.loadRepositoryCommits(
                    value.split("/")[0],
                    value.split("/")[1]
                )
            } ?: EmptyLiveData.create()
        }
    }

    override fun setOwnerAndRepositoryName(ownerAndRepositoryName: String) {
        this.ownerAndRepositoryName.value = ownerAndRepositoryName
    }

}
