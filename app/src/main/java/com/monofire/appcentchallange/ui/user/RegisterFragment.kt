package com.monofire.appcentchallange.ui.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.monofire.appcentchallange.R
import com.monofire.appcentchallange.db.RegisterHelper
import com.monofire.appcentchallange.db.ShareDb
import com.monofire.appcentchallange.event.snackbar
import com.monofire.appcentchallange.listener.UserCheckListener
import com.monofire.appcentchallange.model.User
import com.monofire.appcentchallange.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment(), UserCheckListener {
    lateinit var registerHelper: RegisterHelper
    private var nickName = ""
    private var userMail = ""
    private var userPassword = ""
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_btn.setOnClickListener {
            nickName = register_nick_name.editText?.text.toString().trim()
            userMail = register_eMail.editText?.text.toString().trim()
            userPassword = register_password.editText?.text.toString().trim()
            user = User(nickName, userMail, userPassword,10)
            registerHelper = RegisterHelper(user)
            registerHelper.registerCheckListener = this

            progressBar.visibility = View.VISIBLE
            if (registerHelper.userInputisCheck()) {
                registerHelper.userSaveData()
            } else {
                progressBar.visibility = View.GONE
                context?.snackbar(requireView(), "Lütfen boş kutucukları doldurunuz.")
            }

        }
    }

    override fun savedUser(isSaved: Boolean) {
        progressBar.visibility = View.GONE
        if (isSaved) {
            ShareDb.editUserData(requireContext(), user)
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        } else {
            context?.snackbar(requireView(), "Hata Oluştu.")
        }
    }

    override fun loggedUser(isLogin: Boolean, errorMessage: String) {

    }
}