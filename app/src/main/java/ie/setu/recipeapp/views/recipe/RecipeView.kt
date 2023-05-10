package ie.setu.recipeapp.views.recipe

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import com.squareup.picasso.Picasso
import ie.setu.recipeapp.R
import ie.setu.recipeapp.databinding.ActivityRecipeBinding
import ie.setu.recipeapp.models.RecipeModel
import timber.log.Timber.i

class RecipeView : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    private lateinit var presenter: RecipePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        i("Recipe activity started...")

        // Make this layout the active view on screen
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        presenter = RecipePresenter(this)

        // Setup views:
        binding.btnSelectImg.setOnClickListener {
            presenter.doSelectImage()
        }
        binding.textTime.setOnClickListener {
            presenter.doSelectTime()
        }
        binding.editTextRecipeDescription.gravity = Gravity.START

    }

    fun showRecipe(recipe: RecipeModel) {
        binding.editTextRecipeTitle.setText(recipe.title)
        binding.editTextRecipeDescription.setText(recipe.description)
        binding.editTextServes.setText(recipe.servings)
        binding.editTextRecipeDescription.setText(recipe.instructions)
        binding.textTime.text = recipe.timeRequired

        if(recipe.image != Uri.EMPTY) {
            binding.btnSelectImg.setText("Change image")
            Picasso.get()
                .load(recipe.image)
                .placeholder(R.drawable.baseline_broken_image_24)
                .into(binding.imageView)
        }
        else {
            Picasso.get()
                .load(R.drawable.recipe)
                .into(binding.imageView)
        }
    }

    fun updateImage(image: Uri) {
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.baseline_broken_image_24)
            .fit()
            .centerCrop()
            .into(binding.imageView)
        binding.btnSelectImg.setText("Change image")
    }

    fun updateTime(time: String) {
        binding.textTime.text = time
    }

}