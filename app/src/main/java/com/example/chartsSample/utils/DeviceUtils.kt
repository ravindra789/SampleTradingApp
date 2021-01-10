package com.example.chartsSample.utils


import io.socket.client.Manager
import java.net.URI
import java.net.URISyntaxException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by RavindraP on 09 January 2021
 */
object DeviceUtils {

    const val INTERVAL_ONE = 1
    const val INTERVAL_THREE = 3
    const val INTERVAL_FIVE = 5
    const val INTERVAL_SEVEN = 7
    const val INTERVAL_NINE = 9
    private const val SOCKET_URI = "http://kaboom.rksv.net"

    fun getSocketInstance(): Manager? {
        return try {
            Manager(URI(SOCKET_URI))
        } catch (e: URISyntaxException) {
            null
        }
    }

    fun epochToDateFormatter(epoch: Long): String {
        val date = Date(epoch * 1000L)
        val sdf = SimpleDateFormat("dd-MMM")
        return sdf.format(date)
    }

}