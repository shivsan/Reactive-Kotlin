package com.reactive.webapp.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HealthController {
    @GetMapping("/health-check")
    public suspend fun healthCheck(): ResponseEntity<String> {
        return ResponseEntity.ok("K Ok!")
    }
}
