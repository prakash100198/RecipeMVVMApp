package com.example.recipemvvm.api

import com.example.recipemvvm.models.Response
import com.example.recipemvvm.models.util.Constants.Companion.API_KEY
import com.example.recipemvvm.network.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface recipeAPI {

    @GET("search")
    suspend fun searchRecipe(
        @Header("Authorization")
        token: String= API_KEY,
        @Query("page")
        page: Int,
        @Query("query")
        query: String
    ): retrofit2.Response<Response>

    @GET("get")
    suspend fun getRecipe(
        @Header("Authorization")
        token: String= API_KEY,
        @Query("id")
        id: Int
    ): retrofit2.Response<RecipeDto>
}