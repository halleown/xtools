package com.halleown.xtools.io

import android.content.Context
import android.util.Log
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import java.nio.charset.Charset


/**
 * 读取存放在assets目录下的静态文件
 */
fun readFileFromAssets(context: Context, fileName: String): String {
    try {
        return context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
    } catch (e: Exception) {
        Log.e("xtools", "readJsonFromAssets: ${fileName} 文件不存在")
        return ""
    }
}


fun readFile(filePath: String): String {
    val result: StringBuilder = StringBuilder()
    val file = File(filePath)
    if (!file.exists()) {
        Log.e("xtools", "readFile: ${filePath}文件不存在")
        return result.toString()
    }
    try {
        // 读取最后100行内容
        val randomAccessFile = RandomAccessFile(filePath, "r")
        val length = randomAccessFile.length()
        var pointer = length - 1
        var newLineCount = 0
        while (pointer >= 0 && newLineCount < 100) {
            randomAccessFile.seek(pointer)
            val c = randomAccessFile.read().toChar()
            if (c == '\n') {
                newLineCount++
            }
            pointer--
        }
        if (pointer < 0) {
            randomAccessFile.seek(0)
        }
        var line: String?
        while (randomAccessFile.readLine().also { line = it } != null) {
            line?.let {
                val string =
                    String(it.toByteArray(charset("ISO-8859-1")), Charset.forName("UTF-8"))
                result.append(string)
            }
        }
        randomAccessFile.close()
        return result.toString()
        // if (outWriger == null) {
        //     outWriger =
        //         BufferedWriter(OutputStreamWriter(FileOutputStream(file, true), "UTF-8"))
        // }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return result.toString()
}