package com.halleown.xtools

import com.halleown.xtools.utils.LogUtil

/**
 * Created by halle on 2026/04/23.
 */
object XToolsConfig {
    /**
     * 全局调试开关
     */
    var isDebug: Boolean = false
        private set


    fun init(debug: Boolean) {
        this.isDebug = debug
        LogUtil.init(isDebug)
    }
}