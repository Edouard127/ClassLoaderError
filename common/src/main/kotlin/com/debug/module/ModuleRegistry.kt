package com.debug.module

import com.debug.core.Loadable
import com.debug.module.ModuleRegistry.modules
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder


object ModuleRegistry : Loadable {
    val modules = mutableSetOf<Module>()

    val moduleNames: Set<String>
        get() = modules.map { it.name }.toSet()

    override fun load(): String {
        Reflections(
            ConfigurationBuilder()
                .addUrls(ClasspathHelper.forJavaClassPath())
                .addUrls(ClasspathHelper.forClassLoader())
                .filterInputsBy { it.contains("debug") }
                .forPackage("com.debug.module.modules")
                .addScanners(Scanners.SubTypes)
        ).getSubTypesOf(Module::class.java).forEach { moduleClass ->
            moduleClass.declaredFields.find {
                it.name == "INSTANCE"
            }?.apply {
                isAccessible = true
                (get(null) as? Module)?.let { module ->
                    modules.add(module)
                }
            }
        }

        return "Registered ${modules.size} modules"
    }
}
