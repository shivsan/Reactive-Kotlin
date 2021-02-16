package com.reactive.webapp.controller

import com.reactive.webapp.entities.Employee
import com.reactive.webapp.service.EmployeeService
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/employees")
class EmployeeController(private val employeeService: EmployeeService) {
    private val employees = listOf(Employee(1, "John"), Employee(2, "Jane"))

    @GetMapping("/health-check")
    public suspend fun healthCheck(): ResponseEntity<String> {
        return ResponseEntity.ok("K Ok!")
    }

    @GetMapping("/{id}")
    public suspend fun get(@PathVariable("id") id: Int): ResponseEntity<Employee> {
        return ResponseEntity.ok(employees.first { employee -> employee.id == id })
    }

    @GetMapping
    public suspend fun getAll(): ResponseEntity<List<Employee>> {
        return ResponseEntity.ok(employees)
    }

    @PostMapping
    public suspend fun create(
        @RequestBody employee: Employee,
        request: ServerHttpRequest
    ): ResponseEntity<Unit> {
        val createdEmployee = employeeService.create(employee).awaitSingle()
        return ResponseEntity.created(URI("${request.uri}/${createdEmployee.id}")).build()
    }
}
