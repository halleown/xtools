package com.joykade.xtools.utils

import android.app.Activity
import android.content.Intent
import androidx.core.os.bundleOf


inline fun <reified T : Activity> Activity.startActivity(vararg args: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundleOf(*args))
    startActivity(intent)
}