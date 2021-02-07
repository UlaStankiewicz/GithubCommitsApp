package pl.kserocki.githubapp.db.api

import androidx.lifecycle.LiveData
import pl.kserocki.githubapp.db.callback.ApiResponse
import pl.kserocki.githubapp.db.entities.GithubCommit
import pl.kserocki.githubapp.db.entities.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    /**
     * Provides information about repository using GitHub API
     *
     * @param owner of the repository
     * @param repo - name of the repository
     * @return LiveData object with repository
     */
    @GET("/repos/{owner}/{repo}")
    fun fetchGithubRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): LiveData<ApiResponse<GithubRepo>>

    /**
     * Provides information about repository commits using GitHub API
     *
     * @param owner of the repository
     * @param repo - name of the repository
     * @return LiveData with list of commit objects
     */
    @GET("/repos/{owner}/{repo}/commits")
    fun fetchGithubRepositoryCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): LiveData<ApiResponse<List<GithubCommit>>>

}
