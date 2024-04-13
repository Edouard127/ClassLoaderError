package com.debug.forge

import net.minecraftforge.fml.loading.FMLLoader

object LoaderInfoImpl {
    @JvmStatic
    fun getVersion(): String =
        FMLLoader.getLoadingModList().getModFileById("debug").versionString()
}
