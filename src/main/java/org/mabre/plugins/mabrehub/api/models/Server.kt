package org.mabre.plugins.mabrehub.api.models

import org.bukkit.Bukkit

object Server {
    val version: String
        get() {
            return Bukkit.getMinecraftVersion()
        }
    val connected: Int
        get() {
            return Bukkit.getOnlinePlayers().size
        }
    val maxPlayers: Int
        get() {
            return Bukkit.getMaxPlayers()
        }
}