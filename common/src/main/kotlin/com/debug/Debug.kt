package com.debug

import com.debug.core.Loader
import net.minecraft.client.MinecraftClient
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Debug {
    const val MOD_NAME = "Debug"
    const val MOD_ID = "debug"
    const val SYMBOL = "d"
    const val APP_ID = "1208614708395900958"
    val VERSION: String = LoaderInfo.getVersion()
    val LOG: Logger = LogManager.getLogger(SYMBOL)
    @JvmStatic val mc: MinecraftClient by lazy { MinecraftClient.getInstance() }

    fun initialize() = Loader.initialize()
}
