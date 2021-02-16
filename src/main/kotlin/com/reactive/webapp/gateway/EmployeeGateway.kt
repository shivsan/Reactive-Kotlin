package com.reactive.webapp.gateway

import org.springframework.web.reactive.function.client.WebClient
import com.reactive.webapp.entities.Employee
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class EmployeeGateway {
    private var client = WebClient.create("http://localhost:8080")

    suspend fun getEmployeeFromRpc(id: Int): Mono<Employee> {
        return client.get()
            .uri("/employees/{id}", id)
            .retrieve()
            .bodyToMono(Employee::class.java)
    }

    suspend fun getEmployeesFromRpc(): Flux<Employee> {

        return client.get()
            .uri("/employees")
            .retrieve()
            .bodyToFlux(Employee::class.java)
    }
}
