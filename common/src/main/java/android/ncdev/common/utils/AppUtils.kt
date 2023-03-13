package android.ncdev.common.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import android.ncdev.common.BuildConfig
import android.ncdev.common.R
import android.net.Uri
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream



fun DialogFragment.showWithStateCheck(fragmentManager: FragmentManager?, tag: String = "") {
    if (fragmentManager != null && fragmentManager.isStateSaved.not()) {
        show(fragmentManager, tag)
    }
}
fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}
fun Context.getColorRes(@ColorRes id:Int) :Int{
    return ContextCompat.getColor(this,id)
}

fun Fragment.getColor(@ColorRes id: Int) = ContextCompat.getColor(requireContext(), id)

fun Context.openUrl(url: String) {
    CustomTabsIntent.Builder().build().launchUrl(this, Uri.parse(url))
}
fun Fragment.getDimen(@DimenRes id: Int) = resources.getDimensionPixelSize(id)

fun RecyclerView.addItemDivider(){
    addItemDecoration(
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
            setDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.divider_item_decoration
                )!!
            )
        }
    )
}

fun Context.isLocationEnabled(): Boolean {
    val locationManager = getSystemService(Context.LOCATION_SERVICE) as? LocationManager ?: return false
    return LocationManagerCompat.isLocationEnabled(locationManager)
}

fun Context.isPermissionsDenied(permissions: List<String>): Boolean {
    for (permission in permissions){
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED)
            return true
    }
    return false
}

fun Context.rateUs() {
    try {
        val uri =
            Uri.parse("https://play.google.com/store/apps/details?id=${packageName}")
        startActivity(Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.android.vending")
        })
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
const val IMAGE_SHARE_REQUEST_CODE = 101
