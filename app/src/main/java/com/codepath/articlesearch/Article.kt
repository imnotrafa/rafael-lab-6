package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Article(
    @SerialName("foodName")
    val foodName : FoodName?,
    @SerialName("calories")
    val calories: Calories
) : java.io.Serializable

@Keep
@Serializable
data class FoodName(
    @SerialName("food")
    val food: String
) : java.io.Serializable

@Keep
@Serializable
data class Calories(
    @SerialName("calories")
    val calories: String? = null
) : java.io.Serializable
