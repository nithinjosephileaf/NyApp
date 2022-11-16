package com.nithi.nyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.nithi.nyapp.R
import com.nithi.nyapp.databinding.FragmentHomeBinding
import com.nithi.nyapp.model.NytResponse
import com.nithi.nyapp.ui.adapter.NewsAdapter
import com.nithi.nyapp.ui.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.log


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewmodel by viewModels<MainViewmodel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //calling news api
        viewmodel.getNews()

        //collecting data from NewsApi
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewmodel.newsResponse.collect {
                    when (it) {
                        is ResponseState.Success -> {
                            (activity as MainActivity).showLoader(false)
                            setData(it.result)
                        }
                        is ResponseState.Loading -> {
                            (activity as MainActivity).showLoader(true)
                                                 }
                        is ResponseState.Failure -> {

                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

    private fun setData(result: NytResponse) {
        val adapter = NewsAdapter {
           it->onClick(it)
        }
        binding.nyRecyclerview.adapter = adapter
        adapter.bindData(result.results)
    }

    private fun onClick(it: com.nithi.nyapp.model.Result) {
        val bundle=Bundle()
        bundle.putString("url",it.url)
        bundle.putString("title",it.title)
        bundle.putString("description",it.abstract)
        val imageUrl=if (it.media.isNotEmpty()) it.media[0].media[2].url else ""
        bundle.putString("image", imageUrl)

        findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
    }


}