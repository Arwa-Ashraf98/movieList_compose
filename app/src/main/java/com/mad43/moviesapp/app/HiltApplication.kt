package com.mad43.moviesapp.app

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.mad43.moviesapp.common.utlis.NetworkChecker
import com.mad43.moviesapp.common.utlis.NetworkConnectivityObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("coil_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        NetworkConnectivityObserver.initNetworkConnectivityObserver(this)
        NetworkChecker().initNetworkChecker(this)
    }
}