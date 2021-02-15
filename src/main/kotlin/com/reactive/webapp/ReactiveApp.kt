package com.reactive.webapp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ReactiveApp {
    companion object {
        @JvmStatic
        public fun main(args: Array<String>) {
            ObjectMapper().registerModule(KotlinModule())
            SpringApplication.run(ReactiveApp::class.java, *args)
        }
    }
}
