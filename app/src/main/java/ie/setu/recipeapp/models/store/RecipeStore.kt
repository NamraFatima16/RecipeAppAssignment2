package ie.setu.recipeapp.models.store

import ie.setu.recipeapp.models.RecipeModel
import java.util.UUID

interface RecipeStore {
    fun create(recipe: RecipeModel)
    fun read(): List<RecipeModel>
    fun update(recipe: RecipeModel)
    fun delete(recipe: RecipeModel)
    fun findById(id:UUID) : RecipeModel?
}