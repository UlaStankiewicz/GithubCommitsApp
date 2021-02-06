package pl.kserocki.githubapp.models

import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = [("id")])
data class GithubRepo(
    var id: Int,
    var name: String
)