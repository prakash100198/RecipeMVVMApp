package com.example.recipemvvm.models

import com.example.recipemvvm.network.RecipeDto
import com.google.gson.annotations.SerializedName

data class Response(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>
)
