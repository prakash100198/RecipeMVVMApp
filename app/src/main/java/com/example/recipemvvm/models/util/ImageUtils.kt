package com.example.recipemvvm.models.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.recipemvvm.R

const val DEFAULT_IMAGE= R.drawable.img

@Composable
fun loadImage(
    url: String,
    @DrawableRes defaultImage: Int= DEFAULT_IMAGE
): MutableState<Bitmap?>{


    val bitmapState: MutableState<Bitmap?> = remember {
        mutableStateOf(null)
    }
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .placeholder(defaultImage)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value= resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })
    return bitmapState

}