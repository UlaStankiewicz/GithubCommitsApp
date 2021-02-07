package pl.kserocki.githubapp.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import java.util.*

@Entity(primaryKeys = ["id"])
data class GithubRepo(
    var id: Int,
    @Embedded var owner: GithubUser,
    var name: String,
    var searchedDate: Date? = Date()
)