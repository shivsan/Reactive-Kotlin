package com.reactive.webapp.repository

import com.reactive.webapp.entities.Employee
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface EmployeeRepository : ReactiveCrudRepository<Employee, Int> {
    fun findEmployeeById(id: Int): Mono<Employee>
}

