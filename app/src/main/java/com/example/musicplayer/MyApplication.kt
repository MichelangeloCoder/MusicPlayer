/**
 * In MyApplication Class extend with Application define val musicPlayerManager extends with MusicPlayerManager by
 * lazy MusicPlayerManager and initialize companion object and inside lateinit var instance MyApplication and impl
 * override fun onCreate method
 */

package com.example.musicplayer

import android.app.Application

class MyApplication : Application() {

    val musicPlayerManager : MusicPlayerManager by lazy { MusicPlayerManager() }

    companion object{

        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}