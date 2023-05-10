package ie.setu.recipeapp.models.store

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.setu.recipeapp.helpers.*
import ie.setu.recipeapp.models.RecipeModel
import timber.log.Timber.i
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "recipes.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<RecipeModel>>() {}.type

class RecipeJSONStore(private val context: Context) : RecipeStore {

    private var recipes = mutableListOf<RecipeModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun create(recipe: RecipeModel) {
        recipes.add(recipe)
        serialize()
    }

    override fun read(): List<RecipeModel> {
        return recipes
    }

    override fun update(recipe: RecipeModel) {
        var foundRecipe = findById(recipe.id)
        i("Updating recipe: $foundRecipe")
        foundRecipe?.let {
            foundRecipe = recipe.copy()
        }
        i("Updated recipe: $foundRecipe")
        serialize()
    }

    override fun delete(recipe: RecipeModel) {
        i("Removing recipe $recipe")
        recipes.remove(recipe)
        serialize()
    }

    override fun findById(id: UUID): RecipeModel? {
        return recipes.find { it.id == id }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(recipes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        recipes = gsonBuilder.fromJson(jsonString, listType)
    }
}

class UriParser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
    override fun deserialize(json: JsonElement?,
                             typeOfT: Type?,
                             context: JsonDeserializationContext?)
    : Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(src: Uri?,
                           typeOfSrc: Type?,
                           context: JsonSerializationContext?)
    : JsonElement {
        return JsonPrimitive(src.toString())
    }
}