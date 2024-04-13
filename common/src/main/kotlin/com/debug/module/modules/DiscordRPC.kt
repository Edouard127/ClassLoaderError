package com.debug.module.modules

import com.debug.Debug
import com.debug.module.Module
import dev.cbyrne.kdiscordipc.KDiscordIPC

object DiscordRPC : Module("DiscordRPC") {
    private val rpc = KDiscordIPC(Debug.APP_ID)
}
