package ie.setu.recipeapp.views.recipeList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.setu.recipeapp.databinding.ActivityRecipeListBinding
import timber.log.Timber.i

class RecipeListView : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        i("Recipe List activity started...")

        // Make this layout the active view on screen
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}