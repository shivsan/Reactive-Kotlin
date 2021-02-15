package com.reactive.webapp.controller

import com.reactive.webapp.entities.Employee
import com.reactive.webapp.gateway.EmployeeGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/external/employees")
class EmployeeExternalController(private val employeeGateway: EmployeeGateway) {
    @GetMapping("/{id}")
    public suspend fun get(@PathVariable("id") id: Int): ResponseEntity<Mono<Employee>> {
        return ResponseEntity.ok(employeeGateway.getEmployeeFromRpc(id))
    }

    @GetMapping
    public suspend fun getAll(): ResponseEntity<Flux<Employee>> {
        return ResponseEntity.ok(employeeGateway.getEmployeesFromRpc())
    }
}
