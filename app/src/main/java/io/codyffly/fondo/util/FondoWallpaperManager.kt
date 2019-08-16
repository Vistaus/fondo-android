package io.codyffly.fondo.util

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import io.codyffly.fondo.service.NotificationService
import java.io.IOException

class FondoWallpaperManager(val context: Context, val path: String) {
    private val wallpaperManager = WallpaperManager.getInstance(context)

    fun setWallpaper() {
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                val uri = getContentUri(context, path)
                val wallIntent = wallpaperManager
                    .getCropAndSetWallpaperIntent(uri)
                wallIntent.setDataAndType(uri, "image/*")
                wallIntent.putExtra("mimeType", "image/*")
                context.startActivity(wallIntent)
            } else {
                wallpaperManager.setBitmap(BitmapFactory.decodeFile(path))
            }

        } catch (e: IOException) {
            Log.d(NotificationService.TAG, e.message)
        }
    }
}