package ie.setu.recipeapp.views.recipeList

import ie.setu.recipeapp.main.MainApp

class RecipeListPresenter(val view: RecipeListView) {

    private var app: MainApp = view.application as MainApp
    private var position: Int = 0

    init {
        // Register callbacks
    }

    fun getRecipes() = app.recipes

    fun doAddRecipe() {
        //Todo
    }

    fun doEditRecipe() {
        //Todo
    }
}