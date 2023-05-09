package ie.setu.recipeapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class RecipeModel(var title: String = "",
                       var description: String = "",
                       var servings: Int = 0,
                       var timeRequired: String = "",
                       var instructions: String = "",
                       val id: UUID = UUID.randomUUID()) : Parcelable