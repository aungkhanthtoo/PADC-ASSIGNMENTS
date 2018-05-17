package me.padc.aungkhanthtoo.series.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mediator.view.*
import me.padc.aungkhanthtoo.series.R


class MediatorFragment : Fragment() {


    private var listener: OnFragmentInteractionListener? = null

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

        val pagerAdapter = PagerAdapter(activity?.supportFragmentManager!!)
        with(pagerAdapter) {
            addPage(BlankFragment(), "ON THE GO")
            addPage(SeriesFragment(), "SERIES")
            addPage(BlankFragment(), "TEACHERS")
        }
        view.viewPager.adapter = pagerAdapter

        view.tabLayout.setupWithViewPager(view.viewPager)

        return view
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val mItemList by lazy { ArrayList<Fragment>() }

        private val mTitleList by lazy { ArrayList<String>() }

        override fun getItem(position: Int) = mItemList[position]

        override fun getCount() = mItemList.size

        override fun getPageTitle(position: Int) = mTitleList[position]

        fun addPage(item: Fragment, title: String) {
            mItemList.add(item)
            mTitleList.add(title)
        }
    }

}
