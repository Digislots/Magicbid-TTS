package com.magicbid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apptomative.api.ApiUtilities
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.magicbid.databinding.ActivityMainBinding
import com.magicbid.util.Adscode
import com.papayacoders.imp.util.SharedPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.util.Date


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adView: AdView
    private var initialLayoutComplete = false
    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)
            val density = outMetrics.density
            var adWidthPixels = binding.adViewContainer.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }
            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this) {}
        adView = AdView(this)
        binding.adViewContainer.addView(adView)
        binding.adViewContainer.viewTreeObserver.addOnGlobalLayoutListener {
            if (!initialLayoutComplete) {
                initialLayoutComplete = true
                //  loadBanner()
                val result = SharedPrefs.getResponseAll(applicationContext)
                var maxCpm = 0
                var maxCpmAdscode = ""
                if (result != null) {
                    try {
                        for (ads in result) {
                            if (ads.ads_type == 1) {
                                if (ads.cpm > maxCpm) {
                                    maxCpm = ads.cpm.toInt()
                                    maxCpmAdscode = ads.adscode
                                    adView.adUnitId = maxCpmAdscode
                                    adView.setAdSize(adSize)
                                    val adRequest = AdRequest.Builder().build()
                                    adView.loadAd(adRequest)

//                                        if (!adRequest.zza().zzl().isEmpty()) {
//
//
//                                        } else {
//                                            Toast.makeText(this@MainActivity, "No add", Toast.LENGTH_SHORT).show()
//
//                                        }

                                }
                            }
                        }

                    } catch (e: Exception) {
                        Log.d("dvbvb", e.toString())
                    }
                }

            }
        }
        binding.next.setOnClickListener {
            startActivity(Intent(this, RewardedinterstitialAd::class.java))

        }
    }


}