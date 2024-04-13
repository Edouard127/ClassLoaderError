package com.debug.core

interface Loadable {
    fun load() = this::class.simpleName?.let { "Loaded $it" } ?: "Loaded"
}
