package com.bilgensoft.fragmentreplaceexample

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var productDetailFragment: ProductDetailFragment? = null
    private val list: ArrayList<Pair<Fragment, Int>> = arrayListOf()
    private var selectedProductFrameLayoutId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productFragmentOne = ProductFragmentOne.newInstance("Product", "One")
        val productFragmentTwo = ProductFragmentTwo.newInstance("Product", "Two")
        val productFragmentThree = ProductFragmentThree.newInstance("Product", "Three")
        val productFragmentFour = ProductFragmentFour.newInstance("Product", "Four")
        val productFragmentFive = ProductFragmentFive.newInstance("Product", "Five")
        val productFragmentSix = ProductFragmentOne.newInstance("Product", "Six")
        val productFragmentSeven = ProductFragmentTwo.newInstance("Product", "Seven")
        val productFragmentEight = ProductFragmentThree.newInstance("Product", "Eight")
        val productFragmentNine = ProductFragmentFour.newInstance("Product", "Nine")
        val productFragmentTen = ProductFragmentFive.newInstance("Product", "Ten")

        val productDetailFragment = ProductDetailFragment.newInstance("Product", "Detail")

        addFragment(productFragmentOne, R.id.productOne)
        addFragment(productFragmentTwo, R.id.productTwo)
        addFragment(productFragmentThree, R.id.productThree)
        addFragment(productFragmentFour, R.id.productFour)
        addFragment(productFragmentFive, R.id.productFive)
        addFragment(productFragmentSix, R.id.productSix)
        addFragment(productFragmentSeven, R.id.productSeven)
        addFragment(productFragmentEight, R.id.productEight)
        addFragment(productFragmentNine, R.id.productNine)
        addFragment(productFragmentTen, R.id.productTen)

        findViewById<FrameLayout>(R.id.productOne).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productTwo).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productThree).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productFour).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productFive).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productSix).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productSeven).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productEight).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productNine).setOnClickListener(this)
        findViewById<FrameLayout>(R.id.productTen).setOnClickListener(this)

        list.add(Pair(productFragmentOne, R.id.productOne))
        list.add(Pair(productFragmentTwo, R.id.productTwo))
        list.add(Pair(productFragmentThree, R.id.productThree))
        list.add(Pair(productFragmentFour, R.id.productFour))
        list.add(Pair(productFragmentFive, R.id.productFive))
        list.add(Pair(productFragmentSix, R.id.productSix))
        list.add(Pair(productFragmentSeven, R.id.productSeven))
        list.add(Pair(productFragmentEight, R.id.productEight))
        list.add(Pair(productFragmentNine, R.id.productNine))
        list.add(Pair(productFragmentTen, R.id.productTen))
    }

    override fun onClick(frameLayoutId: View?) {
        for (pair in list) {
            if (pair.second == frameLayoutId?.id) {
                openProductDetail(pair.first, frameLayoutId.id)
            } else {
                selectedProductFrameLayoutId = frameLayoutId?.id ?: 0
                removeFragment(pair.first)
            }
        }
    }

    override fun onBackPressed() {
        for (pair in list) {
            if (pair.second == selectedProductFrameLayoutId) {
                openProductDetail(pair.first, pair.second)
            }else{
                addFragment(pair.first,pair.second)
            }
        }
    }

    private fun removeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

    private fun openProductDetail(fragment: Fragment, @IdRes id: Int?) {
        if (fragment == null && id == null) {
            return
        }
        supportFragmentManager.beginTransaction().replace(id!!, fragment).commit()
    }

    private fun addFragment(fragment: Fragment, @IdRes id: Int) {
        supportFragmentManager.beginTransaction().add(id, fragment).commit()
    }
}