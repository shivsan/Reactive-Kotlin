package com.reactive.webapp.controller

import com.reactive.webapp.entities.Employee
import com.reactive.webapp.service.EmployeeService
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.net.URI

@RestController
@RequestMapping("/employees")
class EmployeeController(private val employeeService: EmployeeService) {
    @GetMapping("/{id}")
    public suspend fun get(@PathVariable("id") id: Int): ResponseEntity<Employee> {
        val fetchedEmployee = employeeService.getById(id).awaitSingle()
        return ResponseEntity.ok(fetchedEmployee)
    }

    @GetMapping
    public suspend fun getAll(): ResponseEntity<Flux<Employee>> {
        val fetchedEmployees = employeeService.getAll()
        return ResponseEntity.ok(fetchedEmployees)
    }

    @PostMapping
    public suspend fun create(
        @RequestBody employee: Employee,
        request: ServerHttpRequest
    ): ResponseEntity<Unit> {
        val createdEmployee = employeeService.create(employee).awaitSingle()
        return ResponseEntity.created(URI("${request.uri}/${createdEmployee.id}")).build()
    }

    @PutMapping("/{id}")
    public suspend fun update(
        @PathVariable("id") id: Int,
        @RequestBody employee: Employee,
        request: ServerHttpRequest
    ): ResponseEntity<Unit> {
        employeeService.update(employee).awaitSingle()
        return ResponseEntity.noContent().build()
    }
}
