package com.debug.core

import com.debug.Debug
import com.debug.Debug.LOG
import com.debug.module.ModuleRegistry
import kotlin.system.measureTimeMillis

object Loader {
    private val loadables = listOf(
        ModuleRegistry,
    )

    fun initialize() {
        LOG.info("Initializing ${Debug.MOD_NAME} ${Debug.VERSION}")

        val initTime = measureTimeMillis {
            loadables.forEach { loadable ->
                var info: String
                val phaseTime = measureTimeMillis {
                    info = loadable.load()
                }

                LOG.info("$info in ${phaseTime}ms")
            }
        }

        LOG.info("${Debug.MOD_NAME} ${Debug.VERSION} was successfully initialized (${initTime}ms)")
    }
}
