package jp.shirataki707.yamato.core.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat

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

    fun vectorToBitmap(
        context: Context,
        vectorResId: Int,
        width: Int = 64,
        height: Int = 64,
    ): Bitmap {
        // VectorDrawableを取得
        val drawable = ContextCompat.getDrawable(context, vectorResId)
            ?: throw IllegalArgumentException("Drawable not found")

        // Bitmapを作成
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        // Canvasに描画
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}
