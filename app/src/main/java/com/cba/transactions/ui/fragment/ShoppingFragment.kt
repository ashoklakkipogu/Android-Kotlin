package com.cba.transactions.ui.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cba.transactions.R
import com.cba.transactions.databinding.FragmentShoppingBinding
import com.cba.transactions.base.BaseFragment
import com.cba.transactions.data.local.entry.ShoppingItem
import com.cba.transactions.ui.adapter.ShoppingItemAdapter
import com.cba.transactions.viewmodel.ShoppingViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shopping.*
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingFragment @Inject constructor() :
    BaseFragment<FragmentShoppingBinding, ShoppingViewModel>() {
    lateinit var mShoppingItemAdapter: ShoppingItemAdapter
    var list: List<ShoppingItem> = ArrayList()

    override fun getViewModelClass() = ShoppingViewModel::class.java

    override fun getLayoutResourceId() = R.layout.fragment_shopping
    override fun initView() {
        subscribeToObservers()
        setupRecyclerView()
        fabAddShoppingItem.setOnClickListener {
            /*findNavController().navigate(
                R.id.addShoppingItemFragment
            )*/
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            )
        }
    }

    private fun setupRecyclerView() {
        rvShoppingItems.apply {
            mShoppingItemAdapter = ShoppingItemAdapter(list)
            adapter = mShoppingItemAdapter
            layoutManager = LinearLayoutManager(requireContext())
            ItemTouchHelper(itemTouchCallback).attachToRecyclerView(this)
        }
    }

    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val pos = viewHolder.layoutPosition
            val item = mShoppingItemAdapter.shoppingItems(pos)
            viewModel.deleteShoppingItem(item)
            Snackbar.make(requireView(), "Successfully deleted item", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel.insertShoppingItemIntoDb(item)
                }
                show()
            }
        }
    }

    private fun subscribeToObservers() {
        viewModel.shoppingItems.observe(this, Observer {
            mShoppingItemAdapter.updateData(it)
        })
    }
}














