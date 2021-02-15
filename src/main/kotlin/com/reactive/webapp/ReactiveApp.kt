package com.reactive.webapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ReactiveApp {
    companion object {
        @JvmStatic
        public fun main(args: Array<String>) {
            SpringApplication.run(ReactiveApp::class.java, *args)
        }
    }
}
