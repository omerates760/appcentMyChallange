package com.monofire.appcentchallange.ui.user

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.monofire.appcentchallange.R
import com.monofire.appcentchallange.db.FbHelper
import com.monofire.appcentchallange.db.ShareDb
import com.monofire.appcentchallange.event.snackbar
import com.monofire.appcentchallange.listener.UpdatedUserDataListener
import com.monofire.appcentchallange.model.User
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), View.OnClickListener, UpdatedUserDataListener {
    private lateinit var sampleDialog: AlertDialog
    private lateinit var fbHelper: FbHelper
    private var selectedNumber = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fbHelper = FbHelper()
        fbHelper.updateUserListener = this
        userNickname.text = ShareDb.getUserName(requireContext())
        userMail.text = ShareDb.getUserMail(requireContext())
        userPassword.text = ShareDb.getUserPassword(requireContext())
        editNickname.setOnClickListener(this)
        editMail.setOnClickListener(this)
        editPassword.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            editNickname.id -> {
                selectedNumber = 0
                openDialog(userNickname.text.toString(), "NickName")
            }
            editMail.id -> {
                selectedNumber = 1
                openDialog(userMail.text.toString(), "Mail")
            }
            editPassword.id -> {
                selectedNumber = 2
                openDialog(userPassword.text.toString(), "Şifre")

            }
        }
    }

    private fun openDialog(value: String, tag: String) {
        val text = EditText(context)
        text.setText(value)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("$tag güncelle")
        builder.setCancelable(false)
        builder.setView(text)
        builder.setPositiveButton("GÜNCELLE", null)
        builder.setNeutralButton("İPTAL", null)
        sampleDialog = builder.create()
        sampleDialog.show()
        sampleDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            it.isEnabled = false
            sampleDialog.getButton(AlertDialog.BUTTON_NEUTRAL).isEnabled = true
            if (text.text.isNotEmpty()) {
                fbHelper.userUpdateProfile(
                    text.text.toString(), selectedNumber,
                    FirebaseAuth.getInstance().currentUser?.uid.toString()
                )
            } else {
                context?.snackbar(requireView(), "$tag boş bırakılamaz")
                sampleDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = true
            }
        }
    }

    override fun updatedData(value: String) {
        sampleDialog.dismiss()
        when (selectedNumber) {
            0 -> userNickname.text = value
            1 -> userMail.text = value
            2 -> userPassword.text = value
        }
    }
}