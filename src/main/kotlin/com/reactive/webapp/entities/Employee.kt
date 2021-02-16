package com.reactive.webapp.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("employees")
data class Employee(
    @Id
    val id: Int?,
    val name: String
)
