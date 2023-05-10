package ie.setu.recipeapp.helpers

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import timber.log.Timber.i

fun showImagePicker(intentLauncher: ActivityResultLauncher<Intent>, context: Context) {

    var imagePickerTargetIntent = Intent()

    imagePickerTargetIntent.action = Intent.ACTION_OPEN_DOCUMENT
    imagePickerTargetIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
    imagePickerTargetIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    imagePickerTargetIntent.type = "image/*"
    imagePickerTargetIntent = Intent.createChooser(imagePickerTargetIntent, "Select Recipe Image")
    intentLauncher.launch(imagePickerTargetIntent)
}

// This obv allows only up to 23 hours, idk how to increase it
fun showTimePicker(listener: OnTimeSetListener, context: Context) {
    val timePicker = TimePickerDialog(context, listener, 0, 0, true)
    timePicker.show()
}

