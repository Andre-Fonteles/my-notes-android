package dev.fonteles.mynotes.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class FuncResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : FuncResult<T>()
    data class Error(val exception: Exception) : FuncResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}