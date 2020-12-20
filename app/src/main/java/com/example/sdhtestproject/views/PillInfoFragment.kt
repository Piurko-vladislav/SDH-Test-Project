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
import androidx.navigation.fragment.navArgs
import com.example.sdhtestproject.R
import com.example.sdhtestproject.app.MyApplication
import com.example.sdhtestproject.contracts.PillInfoContract
import com.example.sdhtestproject.databinding.PillInfoFragmentBinding
import com.example.sdhtestproject.models.Results
import com.example.sdhtestproject.presenters.PillInfoPresenter
import com.example.sdhtestproject.repositotys.PillsDaoRepository
import com.example.sdhtestproject.repositotys.PillsRepository
import javax.inject.Inject


class PillInfoFragment : Fragment(R.layout.pill_info_fragment), PillInfoContract.View {

    @Inject lateinit var daoRepository: PillsDaoRepository
    @Inject lateinit var pillsRepository: PillsRepository

    private lateinit var presenter : PillInfoContract.Presenter

    private var pillsInfoFragmentBinding: PillInfoFragmentBinding? = null

    private val args: PillInfoFragmentArgs by navArgs()

    private var pillId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pillsInfoFragmentBinding = PillInfoFragmentBinding.inflate(inflater, container, false)

        return pillsInfoFragmentBinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = PillInfoPresenter(daoRepository, pillsRepository)
        pillId = args.pillId
        presenter.getPillInfo(pillId, checkInternetConnection())
        initUI()
    }


    fun initUI(){
        pillsInfoFragmentBinding?.navigationIcon?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun showPillInfo(results: Results) {
        pillsInfoFragmentBinding!!.pillTradeLabel.text = results.trade_label?.name
        pillsInfoFragmentBinding!!.pillManufacturerName.text = results.manufacturer?.name
        pillsInfoFragmentBinding!!.pillPackagingDescription.text = results.packaging?.description
        pillsInfoFragmentBinding!!.pillCompositionDescription.text = results.composition?.discription
        pillsInfoFragmentBinding!!.pillCompositionInn.text = results.composition?.inn?.name
        pillsInfoFragmentBinding!!.pillPharmForm.text = results.composition?.pharm_form?.name
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)

    }


    override fun onDestroyView() {
        pillsInfoFragmentBinding = null
        super.onDestroyView()
    }

    fun checkInternetConnection(): Boolean{
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}