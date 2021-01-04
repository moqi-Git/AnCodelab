package com.github.moqigit.tabbarlab.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.moqigit.tabbarlab.base.BaseFragment
import com.github.moqigit.tabbarlab.databinding.FragmentContentTextBinding

/**
 *
 * created by reol at 1/5/21
 */
class SolutionA: BaseFragment<FragmentContentTextBinding>() {

    override fun getName(): String {
        return "SolutionA"
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContentTextBinding {
        return FragmentContentTextBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.tvContent.text = getName()
        vb.tvContent.setBackgroundColor(Color.RED)
    }

}