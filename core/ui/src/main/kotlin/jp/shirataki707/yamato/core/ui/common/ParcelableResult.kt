package jp.shirataki707.yamato.core.ui.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ParcelableResult<T : Parcelable> : Parcelable {

    @Parcelize
    data class Success<T : Parcelable>(override val result: T) : ParcelableResult<T>()

    @Parcelize
    class Failure<T : Parcelable> : ParcelableResult<T>()

    open val result: T?
        get() = (this as? Success)?.result

    fun copyIfSuccess(copy: (T) -> T): ParcelableResult<T>? {
        return if (this is Success) {
            this.copy(result = copy(this.result))
        } else {
            this
        }
    }
}
