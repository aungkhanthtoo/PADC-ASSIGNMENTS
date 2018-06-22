package me.padc.aungkhanthtoo.series.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mediator.view.*
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.activities.MainActivity
import me.padc.aungkhanthtoo.series.delegates.MeMediateDelegate

class MediatorFragment : BaseFragment(){

    companion object {
        @JvmStatic
        fun newInstance(): MediatorFragment {
            return MediatorFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mediator, container, false)

        val pagerAdapter = PagerAdapter(childFragmentManager)
        with(pagerAdapter) {
            addPage(BlankFragment(), "ON THE GO")
            addPage(SeriesFragment(), "SERIES")
            addPage(BlankFragment(), "TEACHERS")
        }
        view.viewPager.adapter = pagerAdapter

        view.tabLayout.setupWithViewPager(view.viewPager)
        view.tabLayout.getTabAt(1)?.select()

        return view
    }

    override fun onStart() {
        super.onStart()
        val mainActivity = activity as? MainActivity
        mainActivity?.supportActionBar?.title = getString(R.string.mediate_title)
    }

    class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val mItemList = ArrayList<Fragment>()

        private val mTitleList = ArrayList<String>()

        override fun getItem(position: Int) = mItemList[position]

        override fun getCount() = mItemList.size

        override fun getPageTitle(position: Int) = mTitleList[position]

        fun addPage(item: Fragment, title: String) {
            mItemList.add(item)
            mTitleList.add(title)
        }
    }

}
