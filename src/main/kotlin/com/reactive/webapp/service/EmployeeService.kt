package com.reactive.webapp.service

import com.reactive.webapp.entities.Employee
import com.reactive.webapp.repository.EmployeeRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {
    fun create(employee: Employee): Mono<Employee> {
        return employeeRepository.save(employee)
    }

    fun getById(employeeId: Int): Mono<Employee> = employeeRepository.findEmployeeById(employeeId)

    fun update(employee: Employee): Mono<Employee> = employeeRepository.save(employee)
}
