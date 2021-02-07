package pl.kserocki.githubapp.db.entities

import androidx.room.Embedded

data class CommitDetails(
    var message: String,
    @Embedded var committer: CommitCommitter
)