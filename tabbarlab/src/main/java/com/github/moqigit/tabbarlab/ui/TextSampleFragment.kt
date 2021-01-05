package com.github.moqigit.tabbarlab.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.moqigit.tabbarlab.base.BaseFragment
import com.github.moqigit.tabbarlab.databinding.FragmentContentTextBinding

class TextSampleFragment: BaseFragment<FragmentContentTextBinding>() {

    private val colorList = arrayListOf(
        Color.RED,
        Color.YELLOW,
        Color.GREEN,
        Color.BLUE,
        Color.CYAN,
        Color.BLACK
    )

    override fun getName(): String {
        return "TextSampleFragment"
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContentTextBinding {
        return FragmentContentTextBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.tvContent.text = arguments?.getString("text")?:"unknown text"
        vb.tvContent.setBackgroundColor(colorList[arguments?.getString("text")?.toInt()?:2])
    }

}