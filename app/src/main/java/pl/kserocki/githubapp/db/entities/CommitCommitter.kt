package pl.kserocki.githubapp.db.entities

import java.util.*

data class CommitCommitter(
    var name: String,
    var email: String,
    var date: Date
)