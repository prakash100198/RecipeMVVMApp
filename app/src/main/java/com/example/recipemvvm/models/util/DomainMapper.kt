package com.example.recipemvvm.models.util

interface DomainMapper <T, DomainModel> {

    fun mapToDomain(entity: T): DomainModel

    fun mapFromDomain(domainModel: DomainModel): T

}