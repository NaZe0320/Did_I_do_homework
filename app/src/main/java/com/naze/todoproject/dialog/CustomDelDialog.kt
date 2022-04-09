package com.naze.todoproject.dialog

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import com.naze.todoproject.R

class CustomDelDialog (context: Context){

    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener) {
        onClickListener = listener
    }

    fun showDialog() {
        dialog.setContentView(R.layout.custom_delete_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        dialog.findViewById<Button>(R.id.btn_dialog_delete).setOnClickListener {
            onClickListener.onClicked()
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btn_dialog_cancel).setOnClickListener {
            dialog.dismiss()
        }
    }

    interface OnDialogClickListener {
        fun onClicked()
    }
}