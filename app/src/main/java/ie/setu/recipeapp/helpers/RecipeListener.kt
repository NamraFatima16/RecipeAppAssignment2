package ie.setu.recipeapp.helpers

import ie.setu.recipeapp.models.RecipeModel

interface RecipeListener {
    fun onRecipeClick(recipe: RecipeModel, position: Int)
}