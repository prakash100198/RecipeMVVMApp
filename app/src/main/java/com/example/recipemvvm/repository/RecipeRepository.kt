package com.example.recipemvvm.repository

import com.example.recipemvvm.models.Recipe

interface RecipeRepository{

    suspend fun searchRecipe(page: Int, query: String): List<Recipe>

    suspend fun getRecipe(id: Int): Recipe
}