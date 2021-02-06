package pl.kserocki.githubapp.api.repository

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import pl.kserocki.githubapp.api.GithubAPI
import pl.kserocki.githubapp.api.dao.GithubRepoDao
import pl.kserocki.githubapp.models.GithubRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepoRepository @Inject
constructor(
    private val githubRepoDao: GithubRepoDao,
    private val githubAPI: GithubAPI
) {

    fun loadRepository(
        isInternet: Boolean,
        owner: String,
        repositoryName: String
    ): LiveData<Resource<GithubRepo>> {
        return githubAPI.fetchGithubRepository(owner, repositoryName)
    }

    fun loadSavedRepositories(): LiveData<List<GithubRepo>> {
        return githubRepoDao.getSearchedRepositories()
    }

}