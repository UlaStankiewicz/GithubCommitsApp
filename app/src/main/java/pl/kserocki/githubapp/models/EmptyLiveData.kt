package pl.kserocki.githubapp.models

import androidx.lifecycle.LiveData

class EmptyLiveData<T> : LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T> create() = EmptyLiveData<T>()
    }
}