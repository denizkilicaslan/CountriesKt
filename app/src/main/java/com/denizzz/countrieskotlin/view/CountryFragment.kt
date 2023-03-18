package com.denizzz.countrieskotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.denizzz.countrieskotlin.R
import com.denizzz.countrieskotlin.databinding.FragmentCountryBinding
import com.denizzz.countrieskotlin.util.downloadFromUrl
import com.denizzz.countrieskotlin.util.placeholderProgressBar
import com.denizzz.countrieskotlin.viewmodel.CountryViewModel

class CountryFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var countryUuid=0

    private lateinit var viewModel: CountryViewModel
    private lateinit var dataBinding: FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        //_binding = FragmentCountryBinding.inflate(inflater, container, false)
        //val view = binding.root
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid=CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel=ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)



        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country->
            country?.let {
                dataBinding.selectedCountryxml=country
  /*
                _binding?.countryNamefr?.text=country.countryName
                _binding?.countryCapitalfr?.text=country.countryCapital
                _binding?.countryCurrencyfr?.text=country.countryCurrency
                _binding?.countryRegionfr?.text=country.countryRegion
                _binding?.countryLanguagefr?.text=country.countryLanguage

                context?.let {
                    _binding?.countryImagefr?.downloadFromUrl(country.imageUrl,
                        placeholderProgressBar(it))
                }
*/

            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}