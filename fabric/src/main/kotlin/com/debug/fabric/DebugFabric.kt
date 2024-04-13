package com.debug.fabric

import net.fabricmc.api.ClientModInitializer
import com.debug.Debug
import com.debug.Debug.LOG
import com.debug.Debug.MOD_NAME
import com.debug.Debug.VERSION

object DebugFabric : ClientModInitializer {
    override fun onInitializeClient() {
        Debug.initialize()
        LOG.info("$MOD_NAME Fabric $VERSION initialized.")
    }
}
