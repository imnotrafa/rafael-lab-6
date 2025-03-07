package com.codepath.articlesearch

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var foodDao: FoodDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val saveBtn = findViewById<Button>(R.id.saveButton)
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "food-db").build()
        foodDao = db.foodDao()

        val foodNameTextView: TextView = findViewById(R.id.foodText)
        val caloriesTextView: TextView = findViewById(R.id.calText)

        saveBtn.setOnClickListener{
            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
            val newFoodEntity = FoodEntity(
                foodNameTextView.text.toString(),
                caloriesTextView.text.toString()
            )
            lifecycleScope.launch(IO) {
                (application as FoodApplication).db.foodDao().insert(newFoodEntity)
            }


            foodNameTextView.setText("")

            caloriesTextView.setText("")
        }
      }
}
