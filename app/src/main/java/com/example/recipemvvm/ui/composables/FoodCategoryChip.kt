package com.example.recipemvvm.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FoodCategoryChip(
    title: String,
    onClicked: (String)->Unit,
    selected: Boolean
){
    Surface(
        modifier = Modifier
            .padding(5.dp),
        elevation = 10.dp,
        shape = MaterialTheme.shapes.medium,
        color = if(selected) Color.Gray else Color.DarkGray
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = selected,
                    onValueChange = {
                        onClicked(title)
                    }
                )
        ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body2,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
        }

    }

}