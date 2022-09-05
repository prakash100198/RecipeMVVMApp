package com.example.recipemvvm.di

import com.example.recipemvvm.api.recipeAPI
import com.example.recipemvvm.network.RecipeDtoMapper
import com.example.recipemvvm.repository.RecipeRepository
import com.example.recipemvvm.repository.RecipeRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//Here we have build repository dependency for the view model to use
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{

    @Singleton //this here is the scope singleton means the scope of application, as long as application is active this dependency will live
    @Provides
    fun provideRecipeRepository(
        recipeAPI: recipeAPI,
        mapper: RecipeDtoMapper
    ): RecipeRepository{
        return RecipeRepositoryImplementation(
            recipeAPI = recipeAPI,
            mapper= mapper
        )
    }
}