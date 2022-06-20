package com.cba.transactions.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ImagePickFragment::class.java.name -> ImagePickFragment()
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment()
            ShoppingFragment::class.java.name -> ShoppingFragment(
            )
            else -> super.instantiate(classLoader, className)
        }
    }
}