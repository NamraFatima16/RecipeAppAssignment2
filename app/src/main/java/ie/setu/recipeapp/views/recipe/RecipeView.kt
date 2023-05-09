package ie.setu.recipeapp.views.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.setu.recipeapp.databinding.ActivityRecipeBinding
import timber.log.Timber.i

class RecipeView : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        i("Recipe activity started...")
        // Make this layout the active view on screen
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            i("Recipe activity button pressed!")
        }
    }
}