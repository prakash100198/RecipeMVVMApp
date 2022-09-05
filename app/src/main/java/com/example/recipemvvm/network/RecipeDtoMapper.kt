package com.example.recipemvvm.network

import com.example.recipemvvm.models.Recipe
import com.example.recipemvvm.models.util.DomainMapper

class RecipeDtoMapper: DomainMapper<RecipeDto, Recipe> {

    //for mapping network incoming data to our local data class recipe
    override fun mapToDomain(entity: RecipeDto): Recipe {
        return Recipe(
            id = entity.pk,
            title = entity.title,
            publisher = entity.publisher,
            featuredImage = entity.featuredImage,
            rating= entity.rating,
            sourceUrl = entity.sourceUrl,
            description = entity.description,
            cookingInstructions = entity.cookingInstructions,
            ingredients = entity.ingredients?: listOf(),
            dateAdded = entity.dateAdded,
            dateUpdated = entity.dateUpdated
        )
    }

    //for mapping our local data class model to network model in case we want to send any data to server/API
    override fun mapFromDomain(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            publisher = domainModel.publisher,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients?: listOf(),
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated
        )
    }

    //for mapping list of incoming data to list of our local data class
    fun toDomainList(initial : List<RecipeDto>): List<Recipe>{
        return initial.map { mapToDomain(it) }
    }

    //for mapping list of local data class model recipe to network model in case of sending data to API
    fun fromDomainList(initial : List<Recipe>): List<RecipeDto>{
        return initial.map { mapFromDomain(it) }
    }
}