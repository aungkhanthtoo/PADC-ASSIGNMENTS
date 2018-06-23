package me.padc.aungkhanthtoo.series.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_series.view.*
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.activities.ProgramDetailActivity
import me.padc.aungkhanthtoo.series.adapters.SeriesAdapter
import me.padc.aungkhanthtoo.series.data.vo.BaseVO
import me.padc.aungkhanthtoo.series.mvp.presenters.SeriesListPresenter
import me.padc.aungkhanthtoo.series.mvp.views.SeriesListView
import org.jetbrains.anko.design.longSnackbar

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SeriesFragment : BaseFragment(), SeriesListView {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPresenter: SeriesListPresenter

    private lateinit var rvSeries: RecyclerView

    private val mAdapter by lazy { SeriesAdapter(mPresenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        mPresenter = SeriesListPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_series, container, false)
        rvSeries = view.recycler
        with(rvSeries) {
            layoutManager = LinearLayoutManager(SeriesFragment@ this.context)
            adapter = mAdapter
            hasFixedSize()
        }

        mPresenter.onLoadSeriesData()

        return view
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun showSeriesList(data: List<BaseVO>) {
        mAdapter.appendNewItems(data)
    }

    override fun showErrorMsg(msg: String) {
        longSnackbar(rvSeries, msg, "Try Again") {
            mPresenter.onLoadSeriesData()
        }
    }

    override fun showCurrentProgramDetail() {
        startActivity(ProgramDetailActivity.newIntentCurrentProgram(context!!))
    }

    override fun showCategoryProgramDetail(programId: String, categoryId: String) {
        startActivity(ProgramDetailActivity.newIntentCategoryProgram(context!!, programId, categoryId))
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
