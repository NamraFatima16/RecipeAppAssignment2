package ie.setu.recipeapp.main

import android.app.Application
import ie.setu.recipeapp.models.RecipeModel
import ie.setu.recipeapp.models.store.RecipeJSONStore
import ie.setu.recipeapp.models.store.RecipeMemStore
import ie.setu.recipeapp.models.store.RecipeStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var recipes: RecipeStore
    override fun onCreate() {
        super.onCreate()

        //Start the logger
        Timber.plant(Timber.DebugTree())
        i("RecipeApp started...")
        recipes = RecipeJSONStore(applicationContext)
        //recipes = RecipeMemStore()
    }
}