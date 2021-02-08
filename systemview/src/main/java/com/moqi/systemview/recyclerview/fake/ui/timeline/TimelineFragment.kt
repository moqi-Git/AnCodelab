package com.moqi.systemview.recyclerview.fake.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moqi.systemview.databinding.FragmentHomeBinding

class TimelineFragment : Fragment() {

    private lateinit var vm: TimelineViewModel
    private lateinit var vb: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = ViewModelProvider(this).get(TimelineViewModel::class.java)
        vb = FragmentHomeBinding.inflate(inflater, container, false)

        return vb.root
    }
}