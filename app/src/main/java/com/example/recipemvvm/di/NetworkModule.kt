package com.example.recipemvvm.di

import com.example.recipemvvm.api.recipeAPI
import com.example.recipemvvm.models.util.Constants.Companion.BASE_URL
import com.example.recipemvvm.network.RecipeDtoMapper
import com.example.recipemvvm.repository.RecipeRepositoryImplementation
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Here we have build retrofit service as a dependency for the repository to use
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun provideRecipeMapper(): RecipeDtoMapper{
        return RecipeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeApi(): recipeAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(recipeAPI::class.java)
    }

}