package com.reactive.webapp.entities

import org.springframework.data.relational.core.mapping.Table

@Table("employee")
data class Employee(
    val id: Int?,
    val name: String
)
