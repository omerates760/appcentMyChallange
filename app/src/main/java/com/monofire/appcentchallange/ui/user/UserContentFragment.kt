package com.monofire.appcentchallange.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monofire.appcentchallange.R
import com.monofire.appcentchallange.adapter.TabAdapter
import kotlinx.android.synthetic.main.fragment_user_content.*

class UserContentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TabAdapter(activity?.supportFragmentManager!!)
        adapter.run {
            addFragment(LoginFragment(), "Giriş")
            addFragment(RegisterFragment(), "Kayıt Ol")
        }
        viewpager_main.adapter = adapter
        detail_page_tab.setupWithViewPager(viewpager_main)
    }
}