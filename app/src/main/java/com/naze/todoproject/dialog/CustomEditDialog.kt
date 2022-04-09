package com.naze.todoproject.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.naze.todoproject.R

class CustomEditDialog (context: Context){

    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener) {
        onClickListener = listener
    }

    fun showDialog() {
        dialog.setContentView(R.layout.custom_edit_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val edit_sub = dialog.findViewById<EditText>(R.id.edit_dialog_sub)
        val edit_color = dialog.findViewById<ImageView>(R.id.edit_dialog_color_picker)

        edit_color.setOnClickListener {

        }

        dialog.findViewById<Button>(R.id.btn_dialog_edit).setOnClickListener {
            onClickListener.onClicked(edit_color.colorFilter.toString(),edit_sub.toString())
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btn_dialog_cancel).setOnClickListener {
            dialog.dismiss()
        }
    }



    interface OnDialogClickListener {
        fun onClicked(color: String, subject: String)
    }
}