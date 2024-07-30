package com.example.time_tracker.ui

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * @author bybuss
*/

fun openTelegramLink(context: Context, url: String) {
    try {
        val telegramIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            setPackage("org.telegram.messenger")
        }
        val packageManager = context.packageManager
        if (telegramIntent.resolveActivity(packageManager) != null) {
            context.startActivity(telegramIntent)
        } else {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}