package com.example.recipemvvm.repository

import com.example.recipemvvm.api.recipeAPI
import com.example.recipemvvm.models.Recipe
import com.example.recipemvvm.network.RecipeDtoMapper

class RecipeRepositoryImplementation(
    private val recipeAPI: recipeAPI,
    private val mapper: RecipeDtoMapper
): RecipeRepository {
    override suspend fun searchRecipe(page: Int, query: String): List<Recipe> {
        val result = recipeAPI.searchRecipe(page = page, query = query).body()
        return if (result != null) {
            mapper.toDomainList(result.recipes)
        }else listOf()
    }

    override suspend fun getRecipe(id: Int): Recipe {
        val result= recipeAPI.getRecipe(id = id).body()
        return if(result!= null){
            mapper.mapToDomain(result)
        }else Recipe()
    }


}