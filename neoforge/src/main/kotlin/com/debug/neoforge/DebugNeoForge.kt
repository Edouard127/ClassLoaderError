package com.debug.neoforge

import net.neoforged.fml.common.Mod
import com.debug.Debug
import com.debug.Debug.LOG
import com.debug.Debug.MOD_NAME
import com.debug.Debug.VERSION

@Mod(Debug.MOD_ID)
object DebugNeoForge {
    init {
        Debug.initialize()
        LOG.info("$MOD_NAME NeoForge $VERSION initialized.")
    }
}
