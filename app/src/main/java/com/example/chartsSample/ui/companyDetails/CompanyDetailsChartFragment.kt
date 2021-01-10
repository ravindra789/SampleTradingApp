package com.example.chartsSample.ui.companyDetails

import android.graphics.Color
import android.graphics.DashPathEffect
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.chartsSample.R
import com.example.chartsSample.di.components.FragmentComponent
import com.example.chartsSample.ui.base.BaseFragment
import com.example.chartsSample.utils.DeviceUtils
import com.example.chartsSample.utils.DeviceUtils.INTERVAL_FIVE
import com.example.chartsSample.utils.DeviceUtils.INTERVAL_NINE
import com.example.chartsSample.utils.DeviceUtils.INTERVAL_ONE
import com.example.chartsSample.utils.DeviceUtils.INTERVAL_SEVEN
import com.example.chartsSample.utils.DeviceUtils.INTERVAL_THREE
import com.example.chartsSample.utils.DeviceUtils.SOCKET_NAME_SPACE
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.gson.JsonArray
import io.socket.client.Ack
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.fragment_company_details_chart.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.*


/**
 * Created by RavindraP on 08 January 2021
 */
class CompanyDetailsChartFragment : BaseFragment<CompanyDetailsChartFragmentViewModel>() {

    companion object {
        fun newInstance(): CompanyDetailsChartFragment =
            CompanyDetailsChartFragment().apply {}
    }

    private var mSocket: Socket? = null
    private var mLastSelectedTagView: AppCompatTextView? = null
    private val mainScope = MainScope()
    private var isLiveView = false
    private var isHistoricalDataFetched = false

    override fun provideLayoutId(): Int = R.layout.fragment_company_details_chart

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
        val manager = DeviceUtils.getSocketInstance()
        mSocket = manager?.socket(SOCKET_NAME_SPACE)
        setSocketEvents()
        mSocket?.connect()
        setData()
        setChart()
        callTaggedSetOfDara(txt_one_day, INTERVAL_ONE)
        setClickListeners()
    }

    private fun setData() {
        txt_day_high_value.text = "$200"
        txt_day_low_value.text = "$100"
        txt_day_open_value.text = "$180.88"
        txt_day_close_value.text = "$109.90"
        txt_avg_volume_value.text = "5M"
        txt_volume_value.text = "100M"
        txt_52week_high_value.text = "$280.78"
        txt_52week_low_value.text = "$90"
    }

    private fun setChart() {
        mp_chart.description.isEnabled = false
        mp_chart.setDrawGridBackground(false)
        mp_chart.setTouchEnabled(true)
        mp_chart.isDragEnabled = true
        mp_chart.setScaleEnabled(true)
        mp_chart.setPinchZoom(true)

        var xAxis: XAxis
        run {   // // X-Axis Style // //
            xAxis = mp_chart.xAxis
            xAxis.setDrawAxisLine(false)
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(true)
            xAxis.setDrawGridLinesBehindData(false)
            xAxis.setDrawLimitLinesBehindData(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
        }

        var yAxis: YAxis
        run {   // // Y-Axis Style // //
            yAxis = mp_chart.axisLeft
            yAxis.setDrawAxisLine(false)
            yAxis.setDrawGridLines(false)
            yAxis.setDrawLabels(false)
            yAxis.setDrawGridLinesBehindData(false)
            yAxis.setDrawLimitLinesBehindData(false)
        }

        var zAxis: YAxis
        run {
            zAxis = mp_chart.axisRight
            zAxis.setDrawAxisLine(false)
            zAxis.setDrawGridLines(false)
            zAxis.setDrawLabels(false)
            zAxis.setDrawGridLinesBehindData(false)
            zAxis.setDrawLimitLinesBehindData(false)
        }
    }

    private fun setClickListeners() {
        txt_one_day.setOnClickListener {
            callTaggedSetOfDara(txt_one_day, INTERVAL_ONE)
        }
        txt_one_month.setOnClickListener {
            callTaggedSetOfDara(txt_one_month, INTERVAL_THREE)
        }
        txt_three_month.setOnClickListener {
            callTaggedSetOfDara(txt_three_month, INTERVAL_FIVE)
        }
        txt_ytd.setOnClickListener {
            callTaggedSetOfDara(txt_ytd, INTERVAL_SEVEN)
        }
        txt_one_year.setOnClickListener {
            callTaggedSetOfDara(txt_one_year, INTERVAL_NINE)
        }
        txt_live.setOnClickListener {
            callTaggedSetOfDara(txt_live, INTERVAL_ONE)
        }
    }

    private fun callTaggedSetOfDara(tagView: AppCompatTextView?, interval: Int) {
        isLiveView = tagView == txt_live

        isHistoricalDataFetched = false
        if (mLastSelectedTagView == null) {
            mLastSelectedTagView = tagView
            mLastSelectedTagView?.setBackgroundColor(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorPrimary
                )
            )
            mLastSelectedTagView?.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
        } else {
            mLastSelectedTagView?.setBackgroundColor(
                ContextCompat.getColor(
                    activity!!,
                    R.color.white
                )
            )
            mLastSelectedTagView?.setTextColor(ContextCompat.getColor(activity!!, R.color.black))
            mLastSelectedTagView = tagView
            mLastSelectedTagView?.setBackgroundColor(
                ContextCompat.getColor(
                    activity!!,
                    R.color.colorPrimary
                )
            )
            mLastSelectedTagView?.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
        }
        viewModel.getHistoricalData(interval)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.historicalData.observe(this, Observer { jsonArray ->
            jsonArray?.let { jsonArrayItem ->
                isHistoricalDataFetched = true
                drawLineChart(jsonArrayItem)
            }
        })
    }

    private fun drawLineChart(jsonArrayItem: JsonArray) {
        val xAxisArrayList = ArrayList<String>()
        val lineData = LineData()
        mp_chart.data = lineData
        mp_chart.setPinchZoom(false)
        mp_chart.setDrawGridBackground(false)
        val values = ArrayList<Entry>()
        for ((position, data) in jsonArrayItem.withIndex()) {
            val result = data.asString.split(",").map { it.trim() }
            val epochTime = result[0].toLong()
            val open = result[1].toFloat()
            //val high = result[2].toFloat()
            //val low = result[3].toFloat()
            //val close = result[4].toFloat()
            //val volume = result[5].toLong()
            val timeStamp = DeviceUtils.epochToDateFormatter(epochTime)
            xAxisArrayList.add(timeStamp)
            values.add(Entry(position.toFloat(), open))
        }

        val xAxis = mp_chart.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                for ((position, item) in xAxisArrayList.withIndex()) {
                    if (position.toFloat() == value)
                        return item
                }
                return ""
            }
        }

        val set = LineDataSet(values, "")
        set.setDrawIcons(false)
        set.enableDashedLine(10f, 5f, 0f)
        set.color = Color.BLACK
        set.setCircleColor(Color.BLACK)
        set.lineWidth = 1f
        set.circleRadius = 3f
        set.setDrawCircleHole(false)
        set.formLineWidth = 1f
        set.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        set.formSize = 15f

        set.enableDashedHighlightLine(10f, 5f, 0f)

        set.setDrawFilled(true)
        set.fillFormatter = IFillFormatter { _, _ ->
            mp_chart.axisLeft.axisMinimum
        }
        set.fillColor = Color.BLUE
        lineData.addDataSet(set)
        mp_chart.data = lineData
        mp_chart.setVisibleXRangeMaximum(12f)
        mp_chart.moveViewTo((lineData.entryCount).toFloat(), 50f, YAxis.AxisDependency.RIGHT)
    }

    private fun addDynamicEntryToChart(open: Float) {
        val data = mp_chart.data
        val randomDataSetIndex = (Math.random() * data.dataSetCount).toInt()
        val randomSet = data.getDataSetByIndex(randomDataSetIndex)
        data.addEntry(Entry(randomSet.entryCount.toFloat(), open), randomDataSetIndex)
        data.notifyDataChanged()
        mp_chart.notifyDataSetChanged()
        mp_chart.setVisibleXRangeMaximum(12f)
        mp_chart.moveViewTo(data.entryCount.toFloat(), 50f, YAxis.AxisDependency.LEFT)
    }

    private fun setSocketEvents() {
        mSocket?.on(Socket.EVENT_CONNECT, onConnect)
        mSocket?.on(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket?.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        mSocket?.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)

        mSocket?.on(getString(R.string.data)) {
            activity?.runOnUiThread {
                val data = it[0]
                val result = data.toString().split(",").map { it.trim() }
                val open = result[1].toFloat()
                txt_company_price.text = String.format(getString(R.string.company_price), open)
                if (isLiveView && isHistoricalDataFetched) addDynamicEntryToChart(open)

                mainScope.launch {
                    val ack = it[1]
                    delay(500)
                    (ack as Ack).call(1)
                }
            }
        }

        mSocket?.on(getString(R.string.error)) {
            activity?.runOnUiThread {
                Toast.makeText(activity, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        }

        val subEmit = JSONObject()
        subEmit.put(getString(R.string.state), true)
        mSocket?.emit(getString(R.string.sub), subEmit)
    }

    private val onConnect: Emitter.Listener = Emitter.Listener {
        activity?.runOnUiThread {
            Toast.makeText(activity, getString(R.string.aut_connected), Toast.LENGTH_SHORT).show()
        }
    }

    private val onConnectError: Emitter.Listener = Emitter.Listener {
        activity?.runOnUiThread {
            Toast.makeText(activity, getString(R.string.connection_error), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private val onDisconnect: Emitter.Listener = Emitter.Listener {
        activity?.runOnUiThread {
            Toast.makeText(activity, getString(R.string.auto_disconnected), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val unSubEmit = JSONObject()
        unSubEmit.put(getString(R.string.state), false)
        mSocket?.emit(getString(R.string.unsub), unSubEmit)
        mSocket?.disconnect()
        mSocket?.off(getString(R.string.data))
        mSocket?.off(getString(R.string.error))
        mainScope.cancel()
    }
}