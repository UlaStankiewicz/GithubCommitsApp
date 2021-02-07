package pl.kserocki.githubapp.models

sealed class Status {
    object SUCCESS : Status()
    class ERROR(val message: String) : Status()
    object LOADING : Status()
}
