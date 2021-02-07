package pl.kserocki.githubapp.db.repository

import android.util.Log
import androidx.lifecycle.LiveData
import pl.kserocki.githubapp.db.api.GithubAPI
import pl.kserocki.githubapp.db.callback.ApiResponse
import pl.kserocki.githubapp.db.dao.GithubCommitDao
import pl.kserocki.githubapp.db.entities.GithubCommit
import pl.kserocki.githubapp.models.MyResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubCommitRepository @Inject
constructor(
    private val githubCommitDao: GithubCommitDao,
    private val githubAPI: GithubAPI
) {

    fun loadRepositoryCommits(
        owner: String,
        repositoryName: String
    ): LiveData<MyResource<List<GithubCommit>>> {
        return object : FetchRepository<List<GithubCommit>, List<GithubCommit>>() {
            override fun saveFetchData(items: List<GithubCommit>) {
                for (item in items) {
                    item.repositoryName = repositoryName
                    githubCommitDao.insertGithubCommit(githubCommit = item)
                }
            }

            override fun shouldFetch(data: List<GithubCommit>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<GithubCommit>> {
                return githubCommitDao.getGithubCommits(owner, repositoryName)
            }

            override fun fetchService(): LiveData<ApiResponse<List<GithubCommit>>> {
                return githubAPI.fetchGithubRepositoryCommits(owner, repositoryName)
            }

            override fun onFetchFailed() {
                Log.d("TEST", "onFetchFailed: GithubCommitRepository")
            }

        }.asLiveData()
    }

}