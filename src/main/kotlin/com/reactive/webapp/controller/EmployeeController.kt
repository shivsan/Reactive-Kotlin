package com.reactive.webapp.controller

import com.reactive.webapp.entities.Employee
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController {
    private val employees = listOf(Employee(1, "John"), Employee(2, "Jane"))

    @GetMapping("/health-check")
    public suspend fun healthCheck(): ResponseEntity<String> {
        return ResponseEntity.ok("K Ok!")
    }

    @GetMapping("/{id}")
    public suspend fun get(@PathVariable("id") id: Int): ResponseEntity<Employee> {
        return ResponseEntity.ok(employees.first { employee -> employee.id == id })
    }

    @GetMapping()
    public suspend fun getAll(): ResponseEntity<List<Employee>> {
        return ResponseEntity.ok(employees)
    }
}
