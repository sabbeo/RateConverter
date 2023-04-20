package com.example.rateconverter.ui.home

import RateAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.rateconverter.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import com.example.rateconverter.json.MergeConverter
import com.example.rateconverter.httputil.HttpUtil
import com.example.rateconverter.configuration.AppConfig
import com.example.rateconverter.data.AppDatabase
import com.example.rateconverter.json.CurrencyConverter
import com.example.rateconverter.json.RateConverter

import com.example.rateconverter.models.Rate
import fr.cs2i.rateconverterkt.io.IoUtil


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerViewRate: RecyclerView = binding.recyclerViewRate
        recyclerViewRate.layoutManager = LinearLayoutManager(requireContext())

        displayRates()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun  getCurrencies(): String {
        val rawCurrencies = IoUtil.readTextFile(
            requireContext(),
            "data/currencies.json"
        )
        return rawCurrencies
    }

    private suspend fun getRatesFromApi(): String {
        val response = withContext(Dispatchers.IO) {

        }
        return HttpUtil.readUrl(AppConfig.API_RATES)
    }

    private suspend fun getCurrenciesFromApi(): String {
        val response = withContext(Dispatchers.IO) {

        }
        return HttpUtil.readUrl(AppConfig.API_CURRENCIES)
    }

    private fun displayRates() = runBlocking {

        val rates = withContext(Dispatchers.IO){
            //val convertRates = RateConverter.convertRates(getRatesFromApi())
            //val convertCurrencies = CurrencyConverter.convertCurrency(getCurrenciesFromApi())
            val merge = MergeConverter.mergeApiData(getCurrenciesFromApi(), getRatesFromApi())

            val CONTEXTE = requireContext()
            val db = Room.databaseBuilder(
                CONTEXTE,
                AppDatabase::class.java,
                "rates.db"
            ).build()
            // Récupération d'un DAO
            val rateDao = db.rateDao()
            // Insertion des données
            rateDao.insertAll(merge)

            merge
        }


        val recyclerViewRate: RecyclerView = binding.recyclerViewRate
        val adapter = RateAdapter(rates)

        recyclerViewRate.adapter = adapter
    }
}

