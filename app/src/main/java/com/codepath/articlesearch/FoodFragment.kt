package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

private const val TAG = "FoodFragment"
class FoodFragment : Fragment() {
    private val articles = mutableListOf<DisplayFood>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.item_list, container, false)


        articlesRecyclerView = view.findViewById(R.id.food_recycler_view)
        articleAdapter = ArticleAdapter(requireContext(), articles)
        articlesRecyclerView.adapter = articleAdapter
        articlesRecyclerView.layoutManager = LinearLayoutManager(requireContext()).also {
            val divider = DividerItemDecoration(requireContext(), it.orientation)
            articlesRecyclerView.addItemDecoration(divider)
        }


        viewLifecycleOwner.lifecycleScope.launch {
            (requireActivity().application as FoodApplication).db.foodDao().getAll().collect { dbList ->
                val mappedList = dbList.map { DisplayFood(it.food_name, it.food_calories.toString()) }
                articles.clear()
                articles.addAll(mappedList)
                articleAdapter.notifyDataSetChanged()
            }
        }

        val addFoodBtn = view.findViewById<Button>(R.id.addFood)
        addFoodBtn.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }

        return view
    }




    companion object {
        fun newInstance(): FoodFragment {
            return FoodFragment()
        }
    }
}