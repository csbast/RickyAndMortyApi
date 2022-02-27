package com.example.rickyandmortyapi.presentation.characterslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickyandmortyapi.R
import com.example.rickyandmortyapi.presentation.adapter.Adapter
import com.example.rickyandmortyapi.databinding.CharactersListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private lateinit var viewModel: CharactersListViewModel
    private lateinit var mAdapter: Adapter
    private lateinit var binding: CharactersListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.characters_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharactersListViewModel::class.java)
        viewModel.setupNetwork()
        setupUi()
        setupObservers()
    }

    private fun setupUi() {
        mAdapter = Adapter(mutableListOf())
        binding.charactersRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    private fun setupObservers() {
        setupCharacterListObserver()
    }

    private fun setupCharacterListObserver() {
        viewModel.characterListLiveData.observe(viewLifecycleOwner) {
            mAdapter.updateCharacters(it)
        }
    }
}
