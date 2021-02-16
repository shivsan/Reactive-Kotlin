package com.reactive.webapp

import org.h2.tools.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.sql.SQLException

// Because h2 console does not work on a reactive based application
@Component
class H2 {
    private var webServer: Server? = null

    @Value("\${webclientexample.h2-console-port}")
    var h2ConsolePort: Int? = null
    @EventListener(ContextRefreshedEvent::class)
    @Throws(SQLException::class)
    fun start() {
        webServer = Server.createWebServer("-webPort", h2ConsolePort.toString(), "-tcpAllowOthers").start()
    }

    @EventListener(ContextClosedEvent::class)
    fun stop() {
        webServer!!.stop()
    }
}
