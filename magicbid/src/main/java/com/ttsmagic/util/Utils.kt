package com.papayacoders.imp.util

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.magicbid.R

class Utils {

    private var progressDialog: AlertDialog? = null



    public fun showProgress(context: Context) {

        try {
            if (progressDialog == null) {
                progressDialog = MaterialAlertDialogBuilder(context)
                    .setView(R.layout.layout_loading_dialog)
                    .setCancelable(false)
                    .create()
            }
            try {
                if (!progressDialog!!.isShowing)
                    progressDialog!!.show()
            } catch (e: Exception) {
            }
        } catch (e: Exception) {
        }

    }

    public fun hideProgress() {

        try {
            if (progressDialog != null)
                progressDialog!!.dismiss()
        } catch (e: Exception) {
        }

    }

}