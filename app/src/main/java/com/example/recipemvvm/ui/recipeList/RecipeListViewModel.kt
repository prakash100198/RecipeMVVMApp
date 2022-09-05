package com.example.recipemvvm.ui.recipeList

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipemvvm.models.Recipe
import com.example.recipemvvm.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.remember
import androidx.compose.ui.window.isPopupLayout
import com.example.recipemvvm.models.util.Constants.Companion.PAGE_SIZE
import kotlinx.coroutines.delay

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val repository: RecipeRepository
): ViewModel() {
//for compose ui use mutablestate, it's optimized for compose
    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())
//    private val _recipe : MutableLiveData<List<Recipe>> = MutableLiveData() //use mutablelive when using xml
//    val recipes: LiveData<List<Recipe>> get() =  _recipe


    val query = mutableStateOf("")
    //val selectedCategory: MutableState<FoodCategory?> =  mutableStateOf(null)
    val selectedTab= mutableStateOf(0)
    val isLoading= mutableStateOf(false)
    val page= mutableStateOf(1)
    private var recipeListScrollPosition= 0

    private fun incrementPage(){
        page.value= page.value+1
    }

    fun onChangeRecipeScrollPosition(position: Int){
        recipeListScrollPosition= position
    }
    //Append new recipes to current list of recipes
    private fun appendRecipes(recipe: List<Recipe>){ //in parameter we have new list of recipe
        val current= ArrayList(recipes.value)
        current.addAll(recipe)
        recipes.value= current
    }

    fun nextPage(){
        viewModelScope.launch {
            if(recipeListScrollPosition+1 >= (PAGE_SIZE * page.value)){
                isLoading.value= true
                incrementPage()
                Log.d(TAG, "nextPage: triggered : ${page.value}")
                delay(1000)
                if(page.value>1 ){
                    val result= repository.searchRecipe(
                        page = page.value,
                        query= query.value
                    )
                    appendRecipes(result)
                }
                isLoading.value= false
            }
        }
    }

    fun onSearchTextChanged(searchText: String){
        query.value = searchText
    }
//    fun onSelectCategoryChanged(category: String){
//        val newCategory= getFoodCategory(category)
//        selectedCategory.value= newCategory
//        onSearchTextChanged(category)
//
//    }
    fun onSelectTabChanged(index: Int){
        selectedTab.value= index
    }

    private fun resetSearchState(){
        recipes.value= listOf()
        page.value=1
        onChangeRecipeScrollPosition(0)
        if(query.value=="" )
            clearSelectedTab()
    }
    private fun clearSelectedTab(){
        selectedTab.value= 0
    }

    init {
        searchRecipe(query.value)
    }
    fun searchRecipe(query: String) {
        viewModelScope.launch {
            isLoading.value = true
            delay(500)

            resetSearchState()

            val result = repository.searchRecipe(
                page = 1,
                query = query
            )
            recipes.value = result
            isLoading.value = false
            }
    }


}
