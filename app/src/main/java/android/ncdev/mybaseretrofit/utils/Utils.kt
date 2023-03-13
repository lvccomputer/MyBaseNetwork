package android.ncdev.mybaseretrofit.utils

import android.content.Intent
import android.graphics.Bitmap
import android.ncdev.common.utils.IMAGE_SHARE_REQUEST_CODE
import android.ncdev.mybaseretrofit.BuildConfig
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileOutputStream

fun Fragment.shareFile(file: File, requestCode: Int, type: String): File {
    try {
        val uri = FileProvider.getUriForFile(
            requireContext(),
            "${BuildConfig.APPLICATION_ID}.fileprovider",
            file
        )
        val sharingIntent = ShareCompat.IntentBuilder(requireActivity())
            .setType(type)
            .setStream(uri)
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(sharingIntent, requestCode)
    } catch (exception: Exception) {
        file.delete()
    }
    return file
}
fun Fragment.shareBitmap(bitmap: Bitmap): File? {
    var tempFileDirectory: File? = null
    try {
        tempFileDirectory = File(requireContext().cacheDir, "/temp.jpg")
        val outStream = FileOutputStream(tempFileDirectory, false)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        outStream.flush()
        outStream.close()
    } catch (exception: Exception) {
        tempFileDirectory?.delete()
        return null
    }
    return shareFile(tempFileDirectory, IMAGE_SHARE_REQUEST_CODE, "image/jpeg")
}