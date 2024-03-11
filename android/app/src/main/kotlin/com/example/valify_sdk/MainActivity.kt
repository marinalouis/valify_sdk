package com.example.valify_sdk
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import me.vidv.vidvlivenesssdk.sdk.CapturedActions
import me.vidv.vidvlivenesssdk.sdk.VIDVLivenessConfig
import me.vidv.vidvlivenesssdk.sdk.VIDVLivenessListener
import me.vidv.vidvlivenesssdk.sdk.VIDVLivenessResponse
import me.vidv.vidvocrsdk.sdk.*
import me.vidv.vidvocrsdk.viewmodel.VIDVError
import me.vidv.vidvocrsdk.viewmodel.VIDVEvent
import me.vidv.vidvocrsdk.viewmodel.VIDVLogListener

class MainActivity : FlutterActivity() {
    private val CHANNEL = "newMethod"
    private lateinit var vidvOcrBuilder: VIDVOCRConfig.Builder



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeVIDVOCRBuilder()
//        initializeVIDVLIVENESSBuilder()
        flutterEngine?.dartExecutor?.binaryMessenger?.let {
            MethodChannel(it, CHANNEL)
                .setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                    if (call.method == "nativeMethod") {
                        vidvOcrBuilder.start(this@MainActivity, VIDVOCRListenerImpl(this@MainActivity))
                        Toast.makeText(this, "method channel", Toast.LENGTH_LONG).show()
                        result.success(null)

                    } else {
                        result.notImplemented()
                    }
                }
        }
    }


    private fun initializeVIDVOCRBuilder() {
        vidvOcrBuilder = VIDVOCRConfig.Builder()
            .setBaseUrl("https://www.valifystage.com/")
            .setAccessToken("q4pDxGuRg5HnzPiNmACrYt6mGcwJ12")
            .setBundleKey("f7d64cbdc7c84502b59d017bc2c1a9bb")

        vidvOcrBuilder.setLogsListener(object : VIDVLogListener {
            override fun onLog(log: VIDVEvent) {
                Log.d("VIDV-Logs",
                    "Key: ${log.key}, " +
                            "Session ID: ${log.sessionID}, " +
                            "Date: ${log.date}, " +
                            "Time Stamp: ${log.timestamp}, " +
                            "Type: ${log.type}, " +
                            "Screen: ${log.screen}"
                )
            }

            override fun onLog(log: VIDVError) {
                Log.d("VIDV-Error",
                    "Code: ${log.code}, " +
                            "Message: ${log.message}, " +
                            "Session ID: ${log.sessionId}, " +
                            "Date: ${log.date}, " +
                            "Time Stamp: ${log.timestamp}, " +
                            "Type: ${log.type}, " +
                            "Screen: ${log.screen}"
                )
            }
        })
    }

}


