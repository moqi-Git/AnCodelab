package com.moqi.androidlab.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moqi.androidlab.databinding.MainFragmentBinding

/**
 *
 * created by reol at 2/15/21
 */
class HomeFragment : Fragment() {

    private val TAG = "HOME"

    private lateinit var vb: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vb = MainFragmentBinding.inflate(inflater, container, false)
        vb.message.text = TAG
        return vb.root
    }
}