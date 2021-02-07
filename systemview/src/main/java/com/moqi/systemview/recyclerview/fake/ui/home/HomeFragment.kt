package com.moqi.systemview.recyclerview.fake.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moqi.systemview.databinding.FragmentHomeBinding
import com.moqi.systemview.recyclerview.FreedomAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var vb: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        vb = FragmentHomeBinding.inflate(inflater, container, false)
        vb.rvHome.layoutManager = LinearLayoutManager(requireContext())

        val adapter = FreedomAdapter(mutableListOf())
        vb.rvHome.adapter = adapter
        homeViewModel.contentData.observe(viewLifecycleOwner){
            adapter.setNewList(it)
        }

        return vb.root
    }

}