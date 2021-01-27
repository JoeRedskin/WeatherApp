package com.example.weatherapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CityFragmentBinding
import com.example.weatherapp.model.CityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CityFragment : Fragment() {

//    private val binding: CityFragmentBinding by lazy {
//        DataBindingUtil.setContentView(requireActivity(), R.layout.city_fragment)
//    }
//    private val viewModel by viewModel<CityViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        val adapter = CityAdapter()
//        binding.lifecycleOwner = this
//        binding.viewmodel = viewModel
//        binding.recyclerView.adapter = adapter
//
////        viewModel.deleteCities()
//        viewModel.citiesList.observe(requireActivity()) {
//            if (!it.isNullOrEmpty()) {
//                adapter.updateList(it)
//            }
//        }
        Log.d("tab","dsdsds")
//        return binding.root
        return inflater.inflate(R.layout.city_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_search, menu)
//        val searchItem = menu.findItem(R.id.action_search)
//        val searchView = searchItem.actionView as SearchView
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                viewModel.findCity(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return true
//            }
//        })
//        super.onCreateOptionsMenu(menu, inflater)
//    }
}