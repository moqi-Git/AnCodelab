package com.github.moqigit.tabbarlab.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.moqigit.tabbarlab.base.BaseFragment
import com.github.moqigit.tabbarlab.databinding.FragmentSolutionBBinding

/**
 *
 * created by reol at 1/5/21
 */
class SolutionB: BaseFragment<FragmentSolutionBBinding>() {
    override fun getName(): String {
        return "SolutionB"
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSolutionBBinding {
        return FragmentSolutionBBinding.inflate(inflater, container, false)
    }


}