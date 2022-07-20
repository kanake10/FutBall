package com.example.futball.presentation.playersearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.futball.databinding.FragmentPlayerSearchBinding
import com.example.futball.presentation.adapter.FutBallAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerSearchFragment : Fragment() {
    private val searchAdapter = FutBallAdapter()
    private val viewModel: PlayerSearchViewModel by viewModels()

    private var _binding: FragmentPlayerSearchBinding? = null
    val binding: FragmentPlayerSearchBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.playerSearchRv.apply {
            adapter = searchAdapter
        }

        binding.playerSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let {
                    viewModel.getPlayersSearch(it)
                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealSearchList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressBar.visibility = View.GONE
                    searchAdapter.setContentList(it.toMutableList())
                }
            }
        }

        searchAdapter.itemClickListener {
            findNavController().navigate(
                PlayerSearchFragmentDirections.actionPlayerSearchFragmentToPlayerDetailsFragment(it.idPlayer)

            )
        }
    }
}