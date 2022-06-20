package com.cba.transactions.ui.fragment

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cba.transactions.R
import com.cba.transactions.base.BaseFragment
import com.cba.transactions.common.Result
import com.cba.transactions.databinding.FragmentImagePickBinding
import com.cba.transactions.ui.adapter.ImageAdapter
import com.cba.transactions.utils.Constants.GRID_SPAN_COUNT
import com.cba.transactions.utils.Constants.SEARCH_TIME_DELAY
import com.cba.transactions.viewmodel.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image_pick.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImagePickFragment @Inject constructor() :
    BaseFragment<FragmentImagePickBinding, ShoppingViewModel>() {
    lateinit var mImageAdapter: ImageAdapter
    var list: List<String> = ArrayList()

    override fun getViewModelClass() = ShoppingViewModel::class.java

    override fun getLayoutResourceId() = R.layout.fragment_image_pick
    var viewmodel:ShoppingViewModel? = null


    override fun initView() {
        viewmodel = viewModel

        setupRecyclerView()
        subscribeToObservers()

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(SEARCH_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.searchForImage(editable.toString())
                    }
                }
            }
        }

        mImageAdapter.setOnItemClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set("url", it)
            findNavController().popBackStack()
            //viewModel.setCurImageUrl(it)
        }
    }

    private fun setupRecyclerView() {
        rvImages.apply {
            mImageAdapter = ImageAdapter(list)
            adapter = mImageAdapter
            layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        }
    }

    private fun subscribeToObservers() {
        viewModel.images.observe(viewLifecycleOwner, Observer {
            it?.let { result ->
                when(result.status) {
                    Result.Status.SUCCESS -> {
                        val urls = result.data?.hits?.map { imageResult ->  imageResult.previewURL }
                        mImageAdapter.updateData(urls ?: listOf())
                        progressBar.visibility = View.GONE
                    }
                    Result.Status.ERROR -> {
                        snackBar(result.message ?: "An unknown error occured.")
                        progressBar.visibility = View.GONE
                    }
                    Result.Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}


