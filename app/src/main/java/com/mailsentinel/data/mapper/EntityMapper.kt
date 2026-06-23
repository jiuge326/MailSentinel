package com.mailsentinel.data.mapper

interface EntityMapper<E, D> {
    fun mapFromEntity(entity: E): D
    fun mapToDomain(domain: D): E
}
