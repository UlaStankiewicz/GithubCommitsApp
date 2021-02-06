package pl.kserocki.githubapp.api.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.kserocki.githubapp.models.GithubRepo

@Dao
interface GithubRepoDao {

    @Query("SELECT * FROM GithubRepo WHERE name = :repositoryName COLLATE NOCASE LIMIT 1")
    fun getGithubRepo(repositoryName: String): LiveData<GithubRepo>

    @Query("SELECT * FROM GithubRepo ORDER BY id desc LIMIT 20")
    fun getSearchedRepositories(): LiveData<List<GithubRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGithubRepo(githubRepo: GithubRepo)

    @Query("DELETE FROM GithubRepo")
    fun truncateGithubRepo()

}