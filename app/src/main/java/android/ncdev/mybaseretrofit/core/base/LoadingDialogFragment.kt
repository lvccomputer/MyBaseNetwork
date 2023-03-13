package android.ncdev.mybaseretrofit.core.base

import android.ncdev.common.utils.showWithStateCheck
import android.ncdev.mybaseretrofit.R
import android.ncdev.mybaseretrofit.core.dialog.FullScreenDialog
import androidx.fragment.app.FragmentManager

class LoadingDialogFragment : FullScreenDialog(R.layout.dialog_loading) {

    override var allowBackToCancel = false

    companion object {

        fun show(
            childFragmentManager: FragmentManager,
        ): LoadingDialogFragment {
            return LoadingDialogFragment().apply {
            }.also {
                it.showWithStateCheck(childFragmentManager)
            }
        }
    }
}
