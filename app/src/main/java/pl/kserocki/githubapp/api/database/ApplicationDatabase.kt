package pl.kserocki.githubapp.api.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.kserocki.githubapp.api.dao.GithubRepoDao
import pl.kserocki.githubapp.models.GithubRepo

@Database(entities = [GithubRepo::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
}
