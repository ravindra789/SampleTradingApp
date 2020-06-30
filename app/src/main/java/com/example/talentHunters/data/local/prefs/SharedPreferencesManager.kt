package com.example.talentHunters.data.local.prefs

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.talentHunters.utils.common.Config


/**
 * Created by RavindraP on 30 June 2020
 */
class SharedPreferencesManager {

    private var mContext: Context? = null
    private var prefs: SharedPreferences? = null
    //private var editor: SharedPreferences.Editor? = null

    companion object Factory {
        const val PREF_NAME = "prefs"
        fun create(context: Context): SharedPreferencesManager = SharedPreferencesManager()
            .apply {
            mContext = context
            loadPrefsSchema()
        }
    }

    private fun loadPrefsSchema() {
        prefs = mContext?.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)
    }

    fun setBoolean(key: String?, value: Boolean) {
        val editor: SharedPreferences.Editor? = prefs?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun setInt(key: String?, value: Int) {
        val editor: SharedPreferences.Editor? = prefs?.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun setLong(key: String?, value: Long) {
        val editor: SharedPreferences.Editor? = prefs?.edit()
        editor?.putLong(key, value)
        editor?.apply()
    }

    fun setString(key: String?, value: String) {
        val editor: SharedPreferences.Editor? = prefs?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun getBoolean(key: String?): Boolean {
        return prefs!!.getBoolean(key, false)
    }

    fun getInt(key: String?): Int {
        return prefs!!.getInt(key, 0)
    }

    fun getLong(key: String?): Long {
        return prefs!!.getLong(key, 0)
    }

    fun getString(key: String?): String? {
        return prefs!!.getString(key,
            Config.BLANK
        )
    }

}