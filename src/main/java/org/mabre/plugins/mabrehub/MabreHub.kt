package org.mabre.plugins.mabrehub
import io.javalin.Javalin
import org.bukkit.plugin.java.JavaPlugin
import org.mabre.plugins.mabrehub.api.models.Server

var App: Javalin? = null

class MabreHub : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic

        val classLoader = Thread.currentThread().contextClassLoader
        Thread.currentThread().contextClassLoader = this.classLoader

        if (App == null) {
            val app = Javalin.create()
            app.get("/server") {
                ctx -> ctx.json(Server)
            }
            app.get("/server/version") {
                ctx -> ctx.result(Server.version)
            }
            app.get("/server/connected") {
                ctx -> ctx.result(Server.connected.toString())
            }
            app.get("/server/maxPlayers") {
                ctx -> ctx.result(Server.maxPlayers.toString())
            }
            App = app
        }
        App?.start(7070)

        Thread.currentThread().contextClassLoader = classLoader

        logger.info("Hub plugin enabled!")

    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("Hub plugin disabled...")

        App?.stop()
    }
}