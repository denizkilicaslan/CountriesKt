package com.denizzz.countrieskotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.denizzz.countrieskotlin.databinding.FragmentCountryBinding
import com.denizzz.countrieskotlin.viewmodel.CountryViewModel

class CountryFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var countryUuid=0

    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()


        arguments?.let {
            countryUuid=CountryFragmentArgs.fromBundle(it).countryUuid
        }

        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country->
            country?.let {
                _binding?.countryNamefr?.text=country.countryName
                _binding?.countryCapitalfr?.text=country.countryCapital
                _binding?.countryCurrencyfr?.text=country.countryCurrency
                _binding?.countryRegionfr?.text=country.countryRegion
                _binding?.countryLanguagefr?.text=country.countryLanguage
            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}