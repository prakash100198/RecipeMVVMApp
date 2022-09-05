package com.example.recipemvvm.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipemvvm.ui.recipeList.FoodCategory
import com.example.recipemvvm.ui.recipeList.RecipeListViewModel



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    query: String,
    onSearchTextChanged: (String)->Unit,
    searchRecipe: (String)->Unit,
    onSelectTabChanged: (Int)->Unit,
    selectedTab: Int,
    foodCategories: List<FoodCategory>
){
    val keyboardController =LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = 10.dp,
        color = MaterialTheme.colors.primary
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = query,
                    onValueChange = {
                        onSearchTextChanged(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface),
                    label = {
                        Text(text = "Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            searchRecipe(query)
                            keyboardController?.hide()
                        }
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                )
            }

            //val selectedTab= 0
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth()
            ){
                foodCategories.forEachIndexed{ index, foodCategory ->
                    FoodCategoryChip(
                        title = foodCategory.value,
                        onClicked = {
                            onSelectTabChanged(index)
                            onSearchTextChanged(it)
                            searchRecipe(it)
                        },
                        selected = if(selectedTab==index) selectedTab==index else false
                    )
//                                    Tab(
//                                        selected = selectedTab==index,
//                                        onClick = {
//                                            viewModel.onSelectTabChanged(index)
//                                            viewModel.onSearchTextChanged(foodCategory.value)
//                                            viewModel.searchRecipe(foodCategory.value)
//                                        }
//                                    ){
//                                        Text(
//                                            text = foodCategory.value,
//                                            style = MaterialTheme.typography.body2,
//                                            color = MaterialTheme.colors.secondary,
//                                            modifier = Modifier.padding(8.dp)
//                                        )
//                                    }
                }
            }
        }

    }
}