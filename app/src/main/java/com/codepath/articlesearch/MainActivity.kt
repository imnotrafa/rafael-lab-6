package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch



private const val TAG = "MainActivity/"


class MainActivity : AppCompatActivity() {
    private val articles = mutableListOf<DisplayFood>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    object GlobalData {
        var totalCalories: Int = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val FoodFragment : Fragment = FoodFragment()
        val DanceFragement : Fragment = Dance()
        val SummaryFragment : Fragment = SummaryFragment()
        val addFoodBtn = findViewById<Button>(R.id.addFood)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_sum -> fragment = SummaryFragment
                R.id.nav_food -> fragment = FoodFragment
                R.id.nav_rick -> fragment = DanceFragement
            }
            replaceFragment(fragment)
            true
        }

        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.food_name,
                        entity.food_calories.toString(),
                    )
                }
            }
        }


        addFoodBtn.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }



    }
    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }
}