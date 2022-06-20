package com.cba.transactions.ui.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cba.transactions.R
import com.cba.transactions.base.BaseFragment
import com.cba.transactions.common.Result
import com.cba.transactions.databinding.DataBindingAdapter
import com.cba.transactions.databinding.FragmentAddShoppingItemBinding
import com.cba.transactions.ui.adapter.ShoppingItemAdapter
import com.cba.transactions.viewmodel.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import javax.inject.Inject

@AndroidEntryPoint
class AddShoppingItemFragment @Inject constructor() :
    BaseFragment<FragmentAddShoppingItemBinding, ShoppingViewModel>() {
    lateinit var mShoppingItemAdapter: ShoppingItemAdapter

    override fun getViewModelClass() = ShoppingViewModel::class.java

    override fun getLayoutResourceId() = R.layout.fragment_add_shopping_item
    var viewmodel:ShoppingViewModel? = null


    override fun initView() {
        viewmodel = viewModel

        subscribeToObservers()
        btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                etShoppingItemName.text.toString(),
                etShoppingItemAmount.text.toString(),
                etShoppingItemPrice.text.toString()
            )
        }

        ivShoppingImage.setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }


        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("url")?.observe(
            viewLifecycleOwner) { result ->
            mViewModel.setCurImageUrl(result)
        }



}


    private fun subscribeToObservers() {
        viewModel.curImageUrl.observe(viewLifecycleOwner, Observer {
            DataBindingAdapter.setImage(ivShoppingImage, it)
        })
        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner, Observer {
            it?.let { result ->
                when(result.status) {
                    Result.Status.SUCCESS -> {
                        snackBar("Added Shopping Item")
                        findNavController().popBackStack()
                    }
                    Result.Status.ERROR -> {
                        snackBar(result.message ?: "An unknown error occcured")
                    }
                    Result.Status.LOADING -> {
                        /* NO-OP */
                    }
                    else -> {}
                }
            }
        })
    }
}























