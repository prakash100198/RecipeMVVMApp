package com.example.recipemvvm.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipemvvm.models.Recipe
import com.example.recipemvvm.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.reflect.Constructor
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel
@Inject
constructor(
    private val repository: RecipeRepository
): ViewModel(){

    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val isLoading= mutableStateOf(false)


    fun getRecipe(recipeId: Int){
        viewModelScope.launch {
            isLoading.value= true
            delay(1000)
            val result= repository.getRecipe(recipeId)
            recipe.value= result
            isLoading.value= false
        }
    }


}