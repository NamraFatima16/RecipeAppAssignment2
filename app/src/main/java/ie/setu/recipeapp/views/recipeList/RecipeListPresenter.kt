package ie.setu.recipeapp.views.recipeList

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ie.setu.recipeapp.main.MainApp
import ie.setu.recipeapp.views.recipe.RecipeView


class RecipeListPresenter(private val view: RecipeListView) {

    private var app: MainApp = view.application as MainApp
    private var position: Int = 0

    private lateinit var refreshRecipeListIntentLauncher : ActivityResultLauncher<Intent>

    init {
        // Register callbacks
        this.registerRefreshCallback()
    }

    fun getRecipes() = app.recipes

    fun doAddRecipe() {
        val launcherIntent = Intent(view, RecipeView::class.java)
        refreshRecipeListIntentLauncher.launch(launcherIntent)
    }

    fun doEditRecipe() {
        //Todo
    }

    // Call back for refreshing the recyclerview when we add, delete or edit the recipes
    private fun registerRefreshCallback() {
        refreshRecipeListIntentLauncher = view.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            when(it.resultCode) {
                Activity.RESULT_OK -> view.onRefresh()
            }
        }
    }
}