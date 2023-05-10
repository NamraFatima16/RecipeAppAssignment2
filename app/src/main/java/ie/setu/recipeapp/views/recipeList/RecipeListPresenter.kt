package ie.setu.recipeapp.views.recipeList

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ie.setu.recipeapp.main.MainApp
import ie.setu.recipeapp.models.RecipeModel
import ie.setu.recipeapp.views.recipe.RecipeView
import timber.log.Timber.i


class RecipeListPresenter(private val view: RecipeListView) {

    private var app: MainApp = view.application as MainApp
    private var position: Int = 0

    private lateinit var refreshRecipeListIntentLauncher : ActivityResultLauncher<Intent>

    init {
        // Register callbacks
        this.registerRefreshCallback()
    }

    fun getRecipes() = app.recipes.read()

    fun doAddRecipe() {
        val addRecipeTargetIntent = Intent(view, RecipeView::class.java)
        refreshRecipeListIntentLauncher.launch(addRecipeTargetIntent)
    }

    fun doViewRecipe(recipe: RecipeModel, position: Int) {
        val viewRecipeTargetIntent = Intent(view, RecipeView::class.java)
        viewRecipeTargetIntent.putExtra("recipe_edit", recipe)
        this.position = position
        refreshRecipeListIntentLauncher.launch(viewRecipeTargetIntent)
    }

    // Call back for refreshing the recyclerview when we add, delete or edit the recipes
    private fun registerRefreshCallback() {
        refreshRecipeListIntentLauncher = view.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            when(it.resultCode) {
                Activity.RESULT_OK -> view.onRefresh()
                Activity.RESULT_CANCELED -> i("Operation canceled")
                99 -> view.onDelete(position)
            }
        }
    }
}