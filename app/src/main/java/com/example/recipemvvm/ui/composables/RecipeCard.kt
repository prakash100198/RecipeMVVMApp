package com.example.recipemvvm.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipemvvm.R
import com.example.recipemvvm.models.Recipe
import com.example.recipemvvm.models.util.loadImage

@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: ()->Unit
){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 6.dp, top = 6.dp, start = 12.dp, end = 15.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column {
                recipe.featuredImage?.let { url->
                    val image= loadImage(url = url).value
                    image?.let {
                        Image(bitmap = image.asImageBitmap(),
                            contentDescription = "holder image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(225.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                }
            recipe.title?.let {title->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = TextStyle(fontSize = 16.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold
                            )
                    
                        )
                    Text(
                        text = recipe.rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)

                    )
                }
            }

        }

    }

}