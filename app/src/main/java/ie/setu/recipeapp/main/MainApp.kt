package ie.setu.recipeapp.main

import android.app.Application
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //Start the logger
        Timber.plant(Timber.DebugTree())
        i("RecipeApp started...")
    }
}