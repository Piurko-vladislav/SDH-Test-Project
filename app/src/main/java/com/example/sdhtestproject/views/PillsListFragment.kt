package com.example.sdhtestproject.views

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sdhtestproject.R
import com.example.sdhtestproject.adapters.PillsListAdapter
import com.example.sdhtestproject.contracts.PillsListContract
import com.example.sdhtestproject.databinding.PillsListFragmentBinding
import com.example.sdhtestproject.models.Results
import com.example.sdhtestproject.presenters.PillsListPresenter
import com.example.sdhtestproject.utils.RequestType

class PillsListFragment : Fragment(R.layout.pills_list_fragment), PillsListContract.View, PillsListAdapter.PillsAdapterListener {

    private lateinit var presenter : PillsListContract.Presenter
    private var pillsListFragmentBinding : PillsListFragmentBinding? = null
    private lateinit var adapter: PillsListAdapter
    private lateinit var layoutManager: LinearLayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pillsListFragmentBinding = PillsListFragmentBinding.inflate(inflater, container, false)
        val recyclerView = pillsListFragmentBinding!!.recyclerPillsList
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        adapter = PillsListAdapter(this)
        presenter = PillsListPresenter()

        initBtn()
        recyclerView.adapter = adapter


        return pillsListFragmentBinding!!.root
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)

        presenter.getListOfPills(checkInternetConnection())

    }

    fun initBtn(){
        pillsListFragmentBinding?.nextBtn?.setOnClickListener{
            presenter.getListOfPillsByPage(RequestType.NEXT, checkInternetConnection())
        }
        pillsListFragmentBinding?.previousBtn?.setOnClickListener{
            presenter.getListOfPillsByPage(RequestType.PREVIOUS, checkInternetConnection())
        }
        pillsListFragmentBinding?.fab?.setOnClickListener{
            onSearchBtnClick()
        }
    }

    override fun showListOfPills(pillsList: List<Results>?) {
        if (pillsList != null) {
            adapter.setData(pillsList)
            adapter.notifyDataSetChanged()
        }
    }


    override fun hasNextPage(isHasNext: Boolean) {
        if (isHasNext)
            pillsListFragmentBinding?.nextBtn?.visibility = View.VISIBLE
        else
            pillsListFragmentBinding?.nextBtn?.visibility = View.GONE
    }

    override fun hasPreviousPage(isHasPrevious: Boolean) {
        if (isHasPrevious)
            pillsListFragmentBinding?.previousBtn?.visibility = View.VISIBLE
        else
            pillsListFragmentBinding?.previousBtn?.visibility = View.GONE
    }

    override fun onDestroyView() {
        pillsListFragmentBinding = null
        super.onDestroyView()
    }

    override fun onPillClicked(cardView: View, results: Results) {
        val directions = PillsListFragmentDirections.actionPillsListFragmentToPillsInfoFragment(results.id!!)
        findNavController().navigate(directions)
    }

    fun onSearchBtnClick(){
        val directions = PillsListFragmentDirections.actionPillsListFragmentToSearchFragment()
        findNavController().navigate(directions)
    }

    fun checkInternetConnection(): Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}