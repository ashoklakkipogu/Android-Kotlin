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
            ShoppingSecondFragment::class.java.name -> ShoppingSecondFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}