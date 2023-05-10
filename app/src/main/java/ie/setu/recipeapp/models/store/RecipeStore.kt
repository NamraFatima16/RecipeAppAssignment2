package ie.setu.recipeapp.models.store

import ie.setu.recipeapp.models.RecipeModel

interface RecipeStore {
    fun create(recipe: RecipeModel)
    fun read(): List<RecipeModel>
    fun update(recipe: RecipeModel)
    fun delete(recipe: RecipeModel)
    fun findById(id:Long) : RecipeModel?
}