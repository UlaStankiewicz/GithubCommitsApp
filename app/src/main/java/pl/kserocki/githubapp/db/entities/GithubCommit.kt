package pl.kserocki.githubapp.db.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(primaryKeys = ["sha"])
data class GithubCommit(
    var sha: String,
    @Embedded var author: GithubUser,
    @Embedded var commit: CommitDetails,
    var repositoryName: String = ""
)