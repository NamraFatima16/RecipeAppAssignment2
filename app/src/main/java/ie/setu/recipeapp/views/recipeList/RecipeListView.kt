package ie.setu.recipeapp.views.recipeList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.recipeapp.R
import ie.setu.recipeapp.adapters.RecipeViewAdapter
import ie.setu.recipeapp.databinding.ActivityRecipeListBinding
import ie.setu.recipeapp.helpers.RecipeListener
import ie.setu.recipeapp.models.RecipeModel
import timber.log.Timber.i

class RecipeListView : AppCompatActivity(), RecipeListener {

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var presenter: RecipeListPresenter
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        i("Recipe List activity started...")
        presenter = RecipeListPresenter(this)

        // Make this layout the active view on screen and add toolbar
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        // Set layout manager for recylerView and load the recipes
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecipeViewAdapter(presenter.getRecipes(), this)
        this.onRefresh()

        // Handle FAB clicks
        binding.fab.setOnClickListener {
            presenter.doAddRecipe()
        }
    }
    override fun onRecipeClick(recipe: RecipeModel, position: Int) {
        i("Clicked on recipe ${recipe.id} at position $position")
    }

    fun onRefresh() {
        binding.recyclerView.adapter
        ?.notifyItemRangeChanged(0, presenter.getRecipes().size)
    }

    fun onDelete(position: Int) {
        binding.recyclerView.adapter?.notifyItemRemoved(position)
    }
}