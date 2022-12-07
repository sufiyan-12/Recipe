package com.sufiyan.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso

class RecipeModelAdapter(private val context: Context,
        private val recipeList: ArrayList<RecipeModel>) : RecyclerView.Adapter<RecipeModelAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.itemName.text = recipe.recipeName
        holder.itemDesc.text = recipe.recipeDesc
        val url: String = recipe.image
        Picasso.get().load(url).into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemImage : ImageView
        val itemName: TextView

        val itemDesc: TextView
        init{
            itemImage = itemView.findViewById(R.id.idIVItem)
            itemName = itemView.findViewById(R.id.idTVRecipeName)
            itemDesc = itemView.findViewById(R.id.idTVDesc)
        }
    }
}
