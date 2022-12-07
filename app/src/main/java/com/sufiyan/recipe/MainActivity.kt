package com.sufiyan.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var recipeList: ArrayList<RecipeModel>
    private lateinit var recipeModelAdapter: RecipeModelAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.idRVRecipe)
        recipeList = ArrayList()

        recipeModelAdapter = RecipeModelAdapter(this, recipeList)
        recyclerView.adapter = recipeModelAdapter

        val pbar: ProgressBar = findViewById(R.id.idProgress)
        val home: RelativeLayout = findViewById(R.id.idHome)

        val url: String = "https://www.themealdb.com/api/json/v1/1/categories.php"
        val requestQueue = Volley.newRequestQueue(this@MainActivity)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url,null,
            {response->
                pbar.visibility = View.GONE
                home.visibility = View.VISIBLE
                recipeList.clear()

                try{
                    val categories: JSONArray = response.getJSONArray("categories")
                    for(i in 0..categories.length()){
                        val category:JSONObject = categories.getJSONObject(i)
                        val name: String = category.getString("strCategory")
                        val photo: String = category.getString("strCategoryThumb")
                        val description: String = category.getString("strCategoryDescription")
                        recipeList.add(RecipeModel(name, description, photo))
                    }
                    recipeModelAdapter.notifyDataSetChanged()
                }catch (e: JSONException){
                    e.printStackTrace()
                }

        },{
            Toast.makeText(this,"Try after sometime!!", Toast.LENGTH_SHORT).show()
        })
        requestQueue.add(jsonObjectRequest)
    }
}