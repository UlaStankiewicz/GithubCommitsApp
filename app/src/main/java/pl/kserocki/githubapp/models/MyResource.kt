package pl.kserocki.githubapp.models

class MyResource<out T>(
    val status: Status,
    val data: T?
) {

    companion object {
        fun <T> success(data: T?): MyResource<T> {
            return MyResource(Status.SUCCESS, data)
        }

        fun <T> error(msg: String, data: T?): MyResource<T> {
            return MyResource(Status.ERROR(msg), data)
        }

        fun <T> loading(data: T?): MyResource<T> {
            return MyResource(Status.LOADING, data)
        }
    }
}
