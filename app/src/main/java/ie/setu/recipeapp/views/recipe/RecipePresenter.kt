package ie.setu.recipeapp.views.recipe

import android.app.Activity
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.widget.TimePicker
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ie.setu.recipeapp.helpers.showImagePicker
import ie.setu.recipeapp.helpers.showTimePicker
import ie.setu.recipeapp.main.MainApp
import ie.setu.recipeapp.models.RecipeModel
import timber.log.Timber.i

class RecipePresenter(private val view: RecipeView)
    : OnTimeSetListener {

    var recipe = RecipeModel()
    var app: MainApp = view.application as MainApp
    var edit = false
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>

    init {
        registerImagePickerCallback()

        if (view.intent.hasExtra("recipe_edit")) {
            edit = true
            recipe = view.intent.extras?.getParcelable("recipe_edit")!!
            view.showRecipe(recipe)
        }
    }

    fun doSelectImage() {
        showImagePicker(imageIntentLauncher, view)
    }

    fun doSelectTime() {
        showTimePicker(this, view)
    }

    fun doCancel() {
        view.setResult(Activity.RESULT_CANCELED)
        view.finish()
    }

    fun doDelete() {
        view.setResult(99)
        app.recipes.delete(recipe)
        view.finish()
    }

    fun doSave(title: String, description: String, instructions: String, servings: Int) {
        recipe.title = title
        recipe.description = description
        recipe.servings = servings
        recipe.instructions = instructions

        if(edit) {
            app.recipes.update(recipe)
        }
        else {
            app.recipes.create(recipe)
        }

        view.setResult(Activity.RESULT_OK)
        view.finish()
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher = view.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {result: ActivityResult ->
            when(result.resultCode) {
                Activity.RESULT_OK -> {
                    result.data?.let {
                        val image = it.data!!
                        i("User selected image: $image")
                        view.contentResolver.takePersistableUriPermission(
                            image,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                        recipe.image = image
                        view.updateImage(image)
                    }
                }
                Activity.RESULT_CANCELED -> {
                    i("User canceled operation")
                }
                else -> i("Unhandled result: ${result.resultCode}")
            }
        }
    }

    override fun onTimeSet(timeView: TimePicker?, hourOfDay: Int, minute: Int) {
        i("Set time: $hourOfDay $minute")
        val hrsString = if (hourOfDay == 0) "%9s".format("") else "%2d Hours ".format(hourOfDay)
        val minString = if (minute == 0) "" else "%02d mins".format(minute)
        val timeRequired = "$hrsString$minString"
        view.updateTime(timeRequired)
        recipe.timeRequired = timeRequired
    }
}