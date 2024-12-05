package jp.shirataki707.yamato.core.common.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

object VideoUtils {
    fun openYoutubeVideo(context: Context, videoId: String) {
        val appUri = Uri.parse("vnd.youtube:$videoId")
        val webUri = Uri.parse("https://www.youtube.com/watch?v=$videoId")

        val appIntent = Intent(Intent.ACTION_VIEW, appUri)
        val webIntent = Intent(Intent.ACTION_VIEW, webUri)

        try {
            context.startActivity(appIntent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(webIntent)
        }
    }
}
