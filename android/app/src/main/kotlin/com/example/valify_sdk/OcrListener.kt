package com.example.valify_sdk

import android.app.Activity
import android.content.Context
import android.util.Log
import me.vidv.vidvlivenesssdk.sdk.VIDVLivenessConfig
import me.vidv.vidvocrsdk.sdk.BuilderError
import me.vidv.vidvocrsdk.sdk.CapturedImages
import me.vidv.vidvocrsdk.sdk.ServiceFailure
import me.vidv.vidvocrsdk.sdk.Success
import me.vidv.vidvocrsdk.sdk.UserExit
import me.vidv.vidvocrsdk.sdk.VIDVOCRListener
import me.vidv.vidvocrsdk.sdk.VIDVOCRResponse

class VIDVOCRListenerImpl(private val context: Context) : VIDVOCRListener {
    private lateinit var vidvlivenessBuilder: VIDVLivenessConfig.Builder

    private fun initializeVIDVLIVENESSBuilder() {
        vidvlivenessBuilder = VIDVLivenessConfig.Builder()
            .setBaseUrl("https://www.valifystage.com/")
            .setAccessToken("q4pDxGuRg5HnzPiNmACrYt6mGcwJ12")
            .setBundleKey("f7d64cbdc7c84502b59d017bc2c1a9bb")

    }
    override fun onOCRResult(response: VIDVOCRResponse?) {
        Log.d("success response","$response")

        when (response) {
            is Success -> {
                Log.d("success response","hello success")
                initializeVIDVLIVENESSBuilder()

                // Add your code here
              vidvlivenessBuilder.start(context as Activity, VIDVLivenessListenerImpl())

            }
            is CapturedImages -> {
                // Add your code here
            }
            is UserExit -> {
                // Add your code here
            }
            is ServiceFailure -> {
                // Add your code here
            }
            is BuilderError -> {
                // Add your code here
            }
        }
    }
}