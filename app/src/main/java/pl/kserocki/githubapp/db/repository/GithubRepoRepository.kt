package pl.kserocki.githubapp.db.repository

import android.util.Log
import androidx.lifecycle.LiveData
import pl.kserocki.githubapp.db.api.GithubAPI
import pl.kserocki.githubapp.db.callback.ApiResponse
import pl.kserocki.githubapp.db.dao.GithubRepoDao
import pl.kserocki.githubapp.db.entities.GithubRepo
import pl.kserocki.githubapp.models.EmptyLiveData
import pl.kserocki.githubapp.models.MyResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepoRepository @Inject
constructor(
    private val githubRepoDao: GithubRepoDao,
    private val githubAPI: GithubAPI
) {

    fun loadRepository(owner: String, repositoryName: String): LiveData<MyResource<GithubRepo>> {
        return object : FetchRepository<GithubRepo, GithubRepo>() {
            override fun saveFetchData(items: GithubRepo) {
                githubRepoDao.insertGithubRepo(githubRepo = items)
            }

            override fun shouldFetch(data: GithubRepo?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<GithubRepo> {
                return githubRepoDao.getGithubRepo(owner, repositoryName)
            }

            override fun fetchService(): LiveData<ApiResponse<GithubRepo>> {
                return githubAPI.fetchGithubRepository(owner, repositoryName)
            }

            override fun onFetchFailed() {
                Log.d("TEST", "onFetchFailed: GithubRepoRepository")
            }

        }.asLiveData()
    }

    fun loadSavedRepositories(): LiveData<MyResource<List<GithubRepo>>> {
        return object : FetchRepository<List<GithubRepo>, List<GithubRepo>>() {
            override fun saveFetchData(items: List<GithubRepo>) {
            }

            override fun shouldFetch(data: List<GithubRepo>?): Boolean {
                return false
            }

            override fun loadFromDb(): LiveData<List<GithubRepo>> {
                return githubRepoDao.getSearchedRepositories()
            }

            override fun fetchService(): LiveData<ApiResponse<List<GithubRepo>>> {
                return EmptyLiveData.create()
            }

            override fun onFetchFailed() {
            }
        }.asLiveData()
    }

}