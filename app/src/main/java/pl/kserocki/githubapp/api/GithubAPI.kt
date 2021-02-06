package pl.kserocki.githubapp.api

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.Resource
import pl.kserocki.githubapp.models.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("/repos/{owner}/{repo}")
    fun fetchGithubRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): LiveData<Resource<GithubRepo>>

}
