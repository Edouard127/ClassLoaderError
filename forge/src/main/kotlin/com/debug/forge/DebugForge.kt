package com.debug.forge

import com.debug.Debug
import com.debug.Debug.LOG
import com.debug.Debug.MOD_NAME
import com.debug.Debug.VERSION
import net.minecraftforge.fml.common.Mod


@Mod(Debug.MOD_ID)
object DebugForge {
    init {
        Debug.initialize()
        LOG.info("$MOD_NAME Forge $VERSION initialized.")
    }
}
