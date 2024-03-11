package com.example.valify_sdk

import me.vidv.vidvlivenesssdk.sdk.CapturedActions
import me.vidv.vidvlivenesssdk.sdk.VIDVLivenessListener
import me.vidv.vidvlivenesssdk.sdk.VIDVLivenessResponse
import me.vidv.vidvocrsdk.sdk.BuilderError
import me.vidv.vidvocrsdk.sdk.ServiceFailure
import me.vidv.vidvocrsdk.sdk.Success
import me.vidv.vidvocrsdk.sdk.UserExit

class VIDVLivenessListenerImpl : VIDVLivenessListener {
    override fun onLivenessResult(livenessResponse: VIDVLivenessResponse?) {
        when (livenessResponse) {
            is Success -> {
                // Add your code here
            }
            is BuilderError -> {
                // Add your code here
            }
            is ServiceFailure -> {
                // Add your code here
            }
            is UserExit -> {
                // Add your code here
            }
            is CapturedActions -> {
                // Add your code here
            }
        }
    }
}