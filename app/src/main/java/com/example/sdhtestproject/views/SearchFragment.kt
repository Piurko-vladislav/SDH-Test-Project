package com.example.sdhtestproject.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sdhtestproject.R
import com.example.sdhtestproject.adapters.PillsListAdapter
import com.example.sdhtestproject.contracts.SearchContract
import com.example.sdhtestproject.databinding.SearchFragmentBinding
import com.example.sdhtestproject.models.Results
import com.example.sdhtestproject.presenters.SearchPresenter

class SearchFragment : Fragment(R.layout.search_fragment), SearchContract.View,
    PillsListAdapter.PillsAdapterListener {

    private lateinit var presenter: SearchContract.Presenter
    private var searchFragmentBinding: SearchFragmentBinding? = null
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: PillsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchFragmentBinding = SearchFragmentBinding.inflate(inflater, container, false)
        val recyclerView = searchFragmentBinding!!.searchFragRecyclerPillsList
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = PillsListAdapter(this)

        presenter = SearchPresenter()
        setHasOptionsMenu(true)

        recyclerView.adapter = adapter

        return searchFragmentBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchFragmentBinding?.searchToolbar?.setNavigationOnClickListener { findNavController().navigateUp() }
        presenter.startSearching(searchFragmentBinding!!.searchView)
    }


    override fun showListOfPills(pillsList: List<Results>?) {
        if (pillsList != null) {
            adapter.setData(pillsList)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroyView() {
        searchFragmentBinding = null
        super.onDestroyView()
    }

    override fun onPillClicked(cardView: View, results: Results) {
        val directions =
            SearchFragmentDirections.actionSearchFragmentToPillsInfoFragment(results.id!!)
        findNavController().navigate(directions)
    }

}