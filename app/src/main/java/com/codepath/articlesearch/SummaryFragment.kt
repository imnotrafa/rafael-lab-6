package com.codepath.articlesearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.codepath.articlesearch.MainActivity.GlobalData.totalCalories
import kotlinx.coroutines.launch


class SummaryFragment : Fragment() {
    private lateinit var totalCaloriesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        totalCaloriesTextView = view.findViewById(R.id.total_calories)

        // Fetch the total calories from the Room database
        viewLifecycleOwner.lifecycleScope.launch {


            // Display the total calories in the TextView
            totalCaloriesTextView.text = "Total Calories: $totalCalories"
        }

        return view
    }

    companion object {
        fun newInstance(): SummaryFragment {
            return SummaryFragment()
        }
    }
}