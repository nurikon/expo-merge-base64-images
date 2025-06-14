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
  override fun definition() = ModuleDefinition {
    Name("ExpoMergeBase64Images")

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