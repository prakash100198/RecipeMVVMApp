package com.example.recipemvvm.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipemvvm.ui.BaseApplication
import com.example.recipemvvm.ui.composables.CircularProgressBar
import com.example.recipemvvm.ui.composables.RecipeDetailView
import com.example.recipemvvm.ui.recipeList.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val viewModel: RecipeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { rId->
            viewModel.getRecipe(rId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                val loading= viewModel.isLoading.value
                val recipe= viewModel.recipe.value
                
                Box(modifier = Modifier.fillMaxSize()){
                    
                    if(loading){
                        Text(
                            text = "Loading...",
                            modifier = Modifier.align(Alignment.Center)
                            )
                    }else{
                        recipe?.let { RecipeDetailView(recipe = it, isLoading = loading)}
                    }
                    CircularProgressBar(isDisplayed = loading)
                }
            }
        }
    }
}