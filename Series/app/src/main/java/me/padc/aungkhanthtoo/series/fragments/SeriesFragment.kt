package me.padc.aungkhanthtoo.series.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_series.view.*
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.adapters.SeriesAdapter
import me.padc.aungkhanthtoo.series.data.SeriesModel
import me.padc.aungkhanthtoo.series.delegates.CategoryProgramDelegate
import me.padc.aungkhanthtoo.series.delegates.CurrentProgramDelegate
import me.padc.aungkhanthtoo.series.events.ApiEvents
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SeriesFragment : BaseFragment() {

    private var mDelegate: CurrentProgramDelegate? = null
    private var mCategoryDelgate: CategoryProgramDelegate? = null

    private var param1: String? = null
    private var param2: String? = null
    private val mAdapter by lazy { SeriesAdapter(mDelegate!!, mCategoryDelgate!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_series, container, false)

        with(view.recycler) {
            layoutManager = LinearLayoutManager(SeriesFragment@ this.context)
            adapter = mAdapter
            hasFixedSize()
        }

        return view
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun onDataLoaded(event: ApiEvents.SuccessfulDataLoaded) {
        d("SeriesFragment", "Data Loaded Event is received.")
        mAdapter.setNewItems(event.newData)
    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        if (SeriesModel.mDataSource.isNotEmpty()) {
            mAdapter.setNewItems(SeriesModel.mDataSource)
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CurrentProgramDelegate) {
            mDelegate = context
        } else {
            throw RuntimeException(context.toString() + " must implement CurrentProgramDelegate")
        }
        if (context is CategoryProgramDelegate) {
            mCategoryDelgate = context
        } else {
            throw RuntimeException(context.toString() + " must implement CategoryProgramDelegate")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mDelegate = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SeriesFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
