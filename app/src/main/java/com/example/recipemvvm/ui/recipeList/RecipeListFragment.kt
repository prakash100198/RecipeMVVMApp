package com.example.recipemvvm.ui.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.recipemvvm.R
import com.example.recipemvvm.ui.composables.RecipeCard
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.recipemvvm.models.util.Constants.Companion.PAGE_SIZE
import com.example.recipemvvm.ui.composables.CircularProgressBar
import com.example.recipemvvm.ui.composables.FoodCategoryChip
import com.example.recipemvvm.ui.composables.SearchAppBar
import com.google.android.material.chip.Chip

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()


    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewModel.recipes.observe(viewLifecycleOwner, Observer { }) //used in case of observing live data but now we are using mutablestate in compose

        //return inflater.inflate(R.layout.fragment_recipe_list, container, false) //in case of xml.
        return ComposeView(requireContext()).apply {
            setContent {

                val recipes= viewModel.recipes.value

//                val query by remember{    //one way but most times define this in your viewmodels so that it can persist when screen is rotated.
//                    mutableStateOf("")
//                }
                
                val query = viewModel.query.value
                //val selectedCategory = viewModel.selectedCategory.value
                val foodCategories: List<FoodCategory> =  getFoodCategories()
                val selectedTab= viewModel.selectedTab.value
                val isLoading= viewModel.isLoading.value
                val page= viewModel.page.value

                Column {
                    SearchAppBar(   //state hoisting
                        query = query,
                        onSearchTextChanged = viewModel::onSearchTextChanged,
                        searchRecipe = viewModel::searchRecipe,
                        onSelectTabChanged = viewModel::onSelectTabChanged,
                        selectedTab = selectedTab,
                        foodCategories =foodCategories
                    )

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        LazyColumn{
                            itemsIndexed(
                                items = recipes
                            ){index, recipe->
                                viewModel.onChangeRecipeScrollPosition(index)
                                if((index+1 >= page*PAGE_SIZE) && !isLoading){
                                    viewModel.nextPage()
                                }
                                RecipeCard(recipe = recipe) {
                                    val bundle: Bundle= Bundle()
                                    recipe.id?.let { bundle.putInt("recipeId", it) }
                                    findNavController().navigate(R.id.action_recipeListFragment_to_recipeFragment, bundle)

                                }
                            }
                        }
                        CircularProgressBar(isDisplayed = isLoading)
                    }

                }
                
            }
        }
    }
}