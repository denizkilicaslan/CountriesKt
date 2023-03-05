package com.denizzz.countrieskotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.denizzz.countrieskotlin.adapter.CountryAdapter
import com.denizzz.countrieskotlin.databinding.FragmentFeedBinding
import com.denizzz.countrieskotlin.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter=CountryAdapter(arrayListOf())



    private var _binding: FragmentFeedBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* button deleted
        _binding?.feedButton?.setOnClickListener(){
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
 */

        //ViewModelProviders hangi aktivitede hangi sınıftayız
        //ve hangi ViewModel in sınıfı ile çalışmak itiyoruz bunu söyleyebılıyoruz
        viewModel=ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        _binding?.recyclerCountryList?.layoutManager=LinearLayoutManager(context)
        _binding?.recyclerCountryList?.adapter=countryAdapter

        _binding?.swipeRefreshLayout?.setOnRefreshListener {
                _binding?.recyclerCountryList?.visibility=View.GONE
                _binding?.CountryError?.visibility=View.GONE
                _binding?.CountryLoadingProgress?.visibility=View.VISIBLE

                viewModel.refreshData()
                _binding!!.swipeRefreshLayout.isRefreshing=false

        }

        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->
            countries?.let {
                _binding?.recyclerCountryList?.visibility=View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    _binding?.CountryError?.visibility=View.VISIBLE
                    _binding?.recyclerCountryList?.visibility=View.INVISIBLE
                }else{
                    _binding?.CountryError?.visibility=View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it){
                    _binding?.CountryLoadingProgress?.visibility=View.VISIBLE
                    _binding?.recyclerCountryList?.visibility=View.INVISIBLE
                    _binding?.CountryError?.visibility=View.INVISIBLE
                }else{
                    _binding?.CountryLoadingProgress?.visibility=View.INVISIBLE
                }
            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}