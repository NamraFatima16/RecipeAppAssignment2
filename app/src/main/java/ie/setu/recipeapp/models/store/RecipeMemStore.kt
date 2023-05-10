package ie.setu.recipeapp.models.store

import ie.setu.recipeapp.models.RecipeModel
import timber.log.Timber.i
import java.util.UUID

class RecipeMemStore : RecipeStore {

    private val recipes = ArrayList<RecipeModel>()

    override fun create(recipe: RecipeModel) {
        i("Adding recipe: $recipe")
        recipes.add(recipe)
    }

    override fun read(): List<RecipeModel> {
        return recipes
    }

    override fun update(recipe: RecipeModel) {
        var foundRecipe = findById(recipe.id)
        i("Updating recipe: $foundRecipe")
        foundRecipe?.let {
            recipes.set(recipes.indexOf(it), recipe)
        }
        i("Updated recipe: $foundRecipe")
    }

    override fun delete(recipe: RecipeModel) {
        i("Removing recipe $recipe")
        recipes.remove(recipe)
    }

    override fun findById(id: UUID): RecipeModel? {
        return recipes.find { it.id == id }
    }

}