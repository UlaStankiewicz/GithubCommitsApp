package pl.kserocki.githubapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.kserocki.githubapp.db.entities.GithubCommit

@Dao
interface GithubCommitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGithubCommit(githubCommit: GithubCommit)

    @Query("SELECT * FROM GithubCommit where login = :owner and repositoryName = :repositoryName")
    fun getGithubCommits(owner: String, repositoryName: String): LiveData<List<GithubCommit>>

}