package com.sec.fizz.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sec.fizz.R
import com.sec.fizz.viewmodels.MeViewModel

class MeFragment : Fragment() {

    private lateinit var meViewModel: MeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meViewModel = ViewModelProvider(this)[MeViewModel::class.java]
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

}