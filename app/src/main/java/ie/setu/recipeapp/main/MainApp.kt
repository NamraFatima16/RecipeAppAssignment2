package ie.setu.recipeapp.main

import android.app.Application
import ie.setu.recipeapp.models.RecipeModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val recipes = ArrayList<RecipeModel>()
    override fun onCreate() {
        super.onCreate()

        //Start the logger
        Timber.plant(Timber.DebugTree())
        i("RecipeApp started...")
        recipes.add(RecipeModel("One","aa", 15,"30"))
        recipes.add(RecipeModel("Two","bb",10,"60"))
        recipes.add(RecipeModel("Three","cc", 5,"90"))
    }
}