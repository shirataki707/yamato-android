package jp.shirataki707.yamato.core.common

import android.content.Context

object ResourceUtils {
    /*
     * Get drawable resource ID from file name
     * ex) "back_arrow" -> R.drawable.back_arrow
     */
    @Suppress("Deprecated")
    fun getDrawableResIdFromFileName(
        context: Context,
        fileName: String,
    ): Int? {
        val resourceId = context.resources.getIdentifier(
            fileName.substringBeforeLast('.'),
            "drawable",
            context.packageName,
        )
        return if (resourceId != 0) resourceId else null
    }
}
