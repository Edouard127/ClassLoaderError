package com.debug.fabric

import net.fabricmc.loader.api.FabricLoader

object LoaderInfoImpl {
    @JvmStatic
    fun getVersion(): String =
        FabricLoader.getInstance()
            .getModContainer("debug").orElseThrow()
            .metadata.version.friendlyString
}
