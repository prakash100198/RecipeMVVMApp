package com.example.recipemvvm.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.recipemvvm.models.Recipe
import com.example.recipemvvm.models.util.DEFAULT_IMAGE
import com.example.recipemvvm.models.util.loadImage

@Composable
fun RecipeDetailView(
    recipe: Recipe?,
    isLoading: Boolean= false
){
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ){
            item(
                recipe
            ) {
                recipe?.featuredImage?.let { url->
                    val image= loadImage(url = url, defaultImage = DEFAULT_IMAGE).value
                    image?.let { 
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "image_in detail_recipe",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp))
                {
                    recipe?.title?.let {title->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            Text(
                                text = title,
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .wrapContentWidth(Alignment.Start),
                                style = MaterialTheme.typography.h4
                            )
                            val rank= recipe.rating.toString()
                            Text(
                                text = rank,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                                    .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.h6
                            )
                        }
                        recipe.publisher?.let {publisher->
                            val updated= recipe.dateUpdated
                            Text(
                                text = if(updated!=null){
                                    "Updated $updated by $publisher"
                                }else{
                                    "By $publisher"
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                style = MaterialTheme.typography.caption
                            )
                        }
                        for(ingredient in recipe.ingredients!!){
                            Text(
                                text = ingredient,
                                modifier = Modifier.fillMaxWidth().padding(8.dp),
                                style = MaterialTheme.typography.body1
                                )
                        }

                    }

                }
            }
        }
}