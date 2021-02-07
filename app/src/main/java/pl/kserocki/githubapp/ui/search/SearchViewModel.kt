package pl.kserocki.githubapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import pl.kserocki.githubapp.db.entities.GithubRepo
import pl.kserocki.githubapp.db.repository.GithubRepoRepository
import pl.kserocki.githubapp.models.EmptyLiveData
import pl.kserocki.githubapp.models.MyResource
import javax.inject.Inject

class SearchViewModel @Inject
constructor(private val githubRepoRepository: GithubRepoRepository) : SearchContract.ViewModel() {

    val repositoriesLiveData: LiveData<MyResource<List<GithubRepo>>> =
        githubRepoRepository.loadSavedRepositories()
    val repoLiveData: LiveData<MyResource<GithubRepo>>
    val ownerAndRepoLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        repoLiveData = ownerAndRepoLiveData.switchMap { ownerAndRepo ->
            ownerAndRepoLiveData.value?.let {
                githubRepoRepository.loadRepository(
                    ownerAndRepo.split("/")[0],
                    ownerAndRepo.split("/")[1]
                )
            } ?: EmptyLiveData.create()
        }
    }

}