package pl.kserocki.githubapp.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.kserocki.githubapp.db.converters.DateConverter
import pl.kserocki.githubapp.db.dao.GithubCommitDao
import pl.kserocki.githubapp.db.dao.GithubRepoDao
import pl.kserocki.githubapp.db.entities.GithubCommit
import pl.kserocki.githubapp.db.entities.GithubRepo

@TypeConverters(DateConverter::class)
@Database(entities = [GithubRepo::class, GithubCommit::class], version = 11, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
    abstract fun githubCommitDao(): GithubCommitDao
}
