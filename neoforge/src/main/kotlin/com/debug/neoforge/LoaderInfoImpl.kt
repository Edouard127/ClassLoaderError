package com.lambda.neoforge

import net.neoforged.fml.loading.FMLLoader


object LoaderInfoImpl {
    @JvmStatic
    fun getVersion(): String =
        FMLLoader.getLoadingModList().getModFileById("debug").versionString()
}
