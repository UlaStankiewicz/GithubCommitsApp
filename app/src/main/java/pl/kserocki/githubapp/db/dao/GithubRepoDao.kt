package pl.kserocki.githubapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.kserocki.githubapp.db.entities.GithubRepo

@Dao
interface GithubRepoDao {

    @Query("SELECT * FROM GithubRepo WHERE name = :repositoryName and login = :owner COLLATE NOCASE LIMIT 1")
    fun getGithubRepo(owner: String, repositoryName: String): LiveData<GithubRepo>

    @Query("SELECT * FROM GithubRepo ORDER BY searchedDate desc LIMIT 20")
    fun getSearchedRepositories(): LiveData<List<GithubRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGithubRepo(githubRepo: GithubRepo)

}