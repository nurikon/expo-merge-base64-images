package expo.modules.mergebase64images

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Base64
import java.io.ByteArrayOutputStream
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.net.URL

class ExpoMergeBase64ImagesModule : Module() {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoMergeBase64Images')` in JavaScript.
    Name("ExpoMergeBase64Images")

    // Sets constant properties on the module. Can take a dictionary or a closure that returns a dictionary.
    Constants(
      "PI" to Math.PI
    )

    // Defines event names that the module can send to JavaScript.
    Events("onChange")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("hello") {
      "Hello world! 👋"
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("setValueAsync") { value: String ->
      // Send an event to JavaScript.
      sendEvent("onChange", mapOf(
        "value" to value
      ))
    }

    AsyncFunction("mergeBase64Images") { base64_1: String, base64_2: String ->
      try {
        android.util.Log.d("ExpoMergeBase64Images", "mergeBase64Images called")
        android.util.Log.d("ExpoMergeBase64Images", "base64_1 length: ${base64_1.length}")
        android.util.Log.d("ExpoMergeBase64Images", "base64_2 length: ${base64_2.length}")

        val bitmap1 = base64ToBitmap(base64_1)
        val bitmap2 = base64ToBitmap(base64_2)
        android.util.Log.d("ExpoMergeBase64Images", "Bitmaps decoded")

        val merged = overlayBitmaps(bitmap1, bitmap2)
        android.util.Log.d("ExpoMergeBase64Images", "Bitmaps merged")

        val resultBase64 = bitmapToBase64(merged)
        android.util.Log.d("ExpoMergeBase64Images", "Base64 result created")
        android.util.Log.d("ExpoMergeBase64Images", "Result Base64 (start): ${resultBase64}")

        return@AsyncFunction resultBase64
      } catch (e: Exception) {
        throw e
      }
    }
  }

  private fun base64ToBitmap(base64Str: String): Bitmap {
      val cleanBase64 = base64Str.substringAfter(",")
      val decodedBytes = android.util.Base64.decode(cleanBase64, android.util.Base64.DEFAULT)
      return android.graphics.BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
  }

  private fun overlayBitmaps(base: Bitmap, overlay: Bitmap): Bitmap {
      val scaledOverlay = Bitmap.createScaledBitmap(overlay, base.width, base.height, true)
      val result = Bitmap.createBitmap(base.width, base.height, Bitmap.Config.ARGB_8888)
      val canvas = android.graphics.Canvas(result)
      canvas.drawBitmap(base, 0f, 0f, null)
      canvas.drawBitmap(scaledOverlay, 0f, 0f, null)
      return result
  }

  private fun bitmapToBase64(bitmap: Bitmap): String {
      val outputStream = java.io.ByteArrayOutputStream()
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
      val byteArray = outputStream.toByteArray()
      return "data:image/png;base64," + android.util.Base64.encodeToString(byteArray, android.util.Base64.NO_WRAP)
  }
}
