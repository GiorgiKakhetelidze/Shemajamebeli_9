package com.example.shemajamebeli9.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shemajamebeli9.R
import com.example.shemajamebeli9.base.BaseFragment
import com.example.shemajamebeli9.databinding.HomeFragmentBinding
import com.example.shemajamebeli9.utils.ItemDecorator
import com.example.shemajamebeli9.utils.Resource

class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val homeAdapter = HomeAdapter()

    override fun init() {
        setObservers()
        setRecycler()
    }

    private fun setObservers() {

        viewModel.cards.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    homeAdapter.cardList = resource.data!!
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    private fun setRecycler() {
        binding.recyclerView.apply {
            addItemDecoration(ItemDecorator(marginStart = 50, marginTop = 70))
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = homeAdapter
        }

    }
}