package com.joykade.xtools.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(text: String, isShort: Boolean = true) {
    Toast.makeText(this, text, if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
}