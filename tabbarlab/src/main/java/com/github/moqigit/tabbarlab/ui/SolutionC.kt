package com.github.moqigit.tabbarlab.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.moqigit.tabbarlab.base.BaseFragment
import com.github.moqigit.tabbarlab.databinding.FragmentSolutionCBinding

/**
 *
 * created by reol at 1/5/21
 */
class SolutionC: BaseFragment<FragmentSolutionCBinding>() {
    override fun getName(): String {
        return "SolutionC"
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSolutionCBinding {
        return FragmentSolutionCBinding.inflate(inflater, container, false)
    }
}