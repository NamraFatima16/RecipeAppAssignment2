package ie.setu.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.setu.recipeapp.R
import ie.setu.recipeapp.databinding.RecipeCardBinding
import ie.setu.recipeapp.helpers.RecipeListener
import ie.setu.recipeapp.models.RecipeModel

/**
 * RecipeViewAdapter handles rendering and updating the list of recipes
 * for use with recyclerView
 */
class RecipeViewAdapter(private var recipes: List<RecipeModel>,
                        private val listener: RecipeListener)
    : RecyclerView.Adapter<RecipeViewAdapter.RecipeViewHolder>() {

        // Create view holder for each recipe card
        class RecipeViewHolder(private val binding: RecipeCardBinding)
            : RecyclerView.ViewHolder(binding.root) {

                fun bind(recipe: RecipeModel, listener: RecipeListener) {
                    // Populate the fields of the card
                    val res = binding.root.resources
                    binding.textViewTitle.text = recipe.title
                    binding.textViewDescription.text = recipe.description
                    binding.time.text = res.getString(R.string.required_time_fmt, recipe.timeRequired)
                    binding.servings.text = res.getString(R.string.servings_fmt, recipe.servings)
                    Picasso.get()
                        .load(recipe.image)
                        .placeholder(R.drawable.baseline_broken_image_24)
                        .resize(200,200)
                        .into(binding.imageIcon)

                    // Set a onClickListener to handle when a recipe card is clicked
                    binding.root.setOnClickListener {
                        listener.onRecipeClick(recipe, adapterPosition)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[holder.adapterPosition]
        holder.bind(recipe, listener)
    }

    override fun getItemCount(): Int = recipes.size
}