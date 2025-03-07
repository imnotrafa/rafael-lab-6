package com.codepath.articlesearch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity(
    @ColumnInfo(name = "foodName") val food_name: String?,
    @ColumnInfo(name = "calories") val food_calories: String?,
    @PrimaryKey(autoGenerate = true) val id : Long =0,
)
