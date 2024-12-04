package jp.shirataki707.yamato.core.common.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    inline fun <reified T : Parcelable> nonNullableNavType(): NavType<T> {
        return object : NavType<T>(isNullableAllowed = false) {
            override fun put(bundle: Bundle, key: String, value: T) {
                bundle.putParcelable(key, value)
            }

            override fun get(bundle: Bundle, key: String): T {
                return requireNotNull(
                    BundleCompat.getParcelable(
                        bundle,
                        key,
                        T::class.java,
                    ),
                )
            }

            override fun serializeAsValue(value: T): String {
                return Uri.encode(Json.encodeToString(value))
            }

            override fun parseValue(value: String): T {
                return Json.decodeFromString(value)
            }
        }
    }
}
