package com.github.moqigit.tabbarlab.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 *
 * created by reol at 1/5/21
 */
abstract class BaseFragment<T : ViewBinding> : Fragment() {

    abstract fun getName(): String
    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    protected lateinit var vb: T
        get

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = initViewBinding(inflater, container)
        return vb.root
    }
}