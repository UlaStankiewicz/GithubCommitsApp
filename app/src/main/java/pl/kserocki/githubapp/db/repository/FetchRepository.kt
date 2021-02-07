package pl.kserocki.githubapp.db.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import pl.kserocki.githubapp.db.callback.ApiResponse
import pl.kserocki.githubapp.models.MyResource

abstract class FetchRepository<ResultType, RequestType>
internal constructor() {

    private val result: MediatorLiveData<MyResource<ResultType>> = MediatorLiveData()

    /**
     * Here it checks whether it needs to download data from the Internet or from a local database
     */
    init {
        val loadedFromDB = loadFromDb()
        result.addSource(loadedFromDB) { data ->
            result.removeSource(loadedFromDB)
            if (shouldFetch(data)) {
                result.postValue(MyResource.loading(null))
                fetchFromNetwork(loadedFromDB)
            } else {
                result.addSource<ResultType>(loadedFromDB) { newData ->
                    setValue(
                        MyResource.success(
                            newData
                        )
                    )
                }
            }
        }
    }

    /**
     * Retrieving and converting information into #{MyResource} output
     */
    private fun fetchFromNetwork(loadedFromDB: LiveData<ResultType>) {
        val apiResponse = fetchService()
        result.addSource(apiResponse) { response ->
            response?.let {
                when (response.isSuccessful) {
                    true -> {
                        response.body?.let {
                            saveFetchData(it)
                            val loaded = loadFromDb()
                            result.addSource(loaded) { newData ->
                                newData?.let {
                                    setValue(MyResource.success(newData))
                                }
                            }
                        }
                    }
                    false -> {
                        result.removeSource(loadedFromDB)
                        onFetchFailed()
                        response.let {
                            result.addSource<ResultType>(loadedFromDB) { newData ->
                                setValue(
                                    MyResource.error(it.errorMessage ?: "", newData)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: MyResource<ResultType>) {
        result.value = newValue
    }

    fun asLiveData(): LiveData<MyResource<ResultType>> {
        return result
    }

    @WorkerThread
    protected abstract fun saveFetchData(items: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun fetchService(): LiveData<ApiResponse<RequestType>>

    @MainThread
    protected abstract fun onFetchFailed()
}
