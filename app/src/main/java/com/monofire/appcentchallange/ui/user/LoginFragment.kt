package com.monofire.appcentchallange.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monofire.appcentchallange.R
import com.monofire.appcentchallange.db.LoginHelper
import com.monofire.appcentchallange.db.RegisterHelper
import com.monofire.appcentchallange.event.snackbar
import com.monofire.appcentchallange.listener.UserCheckListener
import com.monofire.appcentchallange.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(), UserCheckListener {
    lateinit var loginHelper: LoginHelper
    private var userMail = ""
    private var userPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userMail = login_mail.editText?.text.toString().trim()
        userPassword = login_password.editText?.text.toString().trim()
        login_btn.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            loginHelper = LoginHelper(userMail, userPassword)
        }
    }

    override fun savedUser(isSaved: Boolean) {

    }

    override fun loggedUser(isLogin: Boolean, errorMessage: String) {
        progressBar.visibility = View.GONE
        if (isLogin) {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            this.activity?.finish()
        } else {
            context?.snackbar(requireView(), errorMessage)
        }
    }

}