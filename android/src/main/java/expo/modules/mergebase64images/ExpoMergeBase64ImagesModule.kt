package expo.modules.mergebase64images

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Base64
import java.io.ByteArrayOutputStream
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import java.net.URL
import android.content.Context
import java.io.File
import java.util.UUID

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

        // Save to cache directory under /cache/Merged/
        val context = appContext.reactContext as Context
        val mergedDir = File(context.cacheDir, "Merged")
        if (!mergedDir.exists()) {
          mergedDir.mkdirs()
        }
        val fileName = "${UUID.randomUUID()}.jpg"
        val outputFile = File(mergedDir, fileName)
        val outputStream = outputFile.outputStream()
        merged.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return@AsyncFunction outputFile.toURI().toString()
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
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
      val byteArray = outputStream.toByteArray()
      return "data:image/jpeg;base64," + Base64.encodeToString(byteArray, Base64.NO_WRAP)
  }
}