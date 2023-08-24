package com.magicbid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apptomative.api.ApiUtilities
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.magicbid.databinding.OpenBinding
import com.papayacoders.imp.util.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OpenActivity : AppCompatActivity() {
    private lateinit var binding: OpenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OpenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.next.setOnClickListener{
            startActivity(Intent(this, RewardedinterstitialAd::class.java))

        }

       // MobileAds.initialize(this) {}
    }
        override fun onBackPressed() {
            // Move the task containing the MainActivity to the back of the activity stack, instead of
            // destroying it. Therefore, MainActivity will be shown when the user switches back to the app.
            moveTaskToBack(true)
        }
//        getInformation()
//
//        binding.next.setOnClickListener{
//            startActivity(Intent(this, RewardedinterstitialAd::class.java))
//
//        }

    private fun getInformation() {
        try {
            lifecycleScope.launch(Dispatchers.IO) {
                val res = ApiUtilities.getApiInterface()!!.getApptomative()
                withContext(Dispatchers.Main) {
                    try {
                        val result = res.body()?.adscode



                        val pubappid = res.body()?.publisherid
                        var maxCpm = 0
                        var ads_type = 0
                        var maxCpmAdscode = ""
                        var appIid = ""

//
//                        if (pubappid != null) {
//                            for (adscode in pubappid) {
//                                appIid = adscode.app_code
//                            }
//
//                            try {
//                                val applicationInfo = packageManager.getApplicationInfo(
//                                    packageName, PackageManager.GET_META_DATA
//                                )
//                                applicationInfo.metaData.putString(
//                                    "com.google.android.gms.ads.APPLICATION_ID",
//                                    appIid
//                                )
//                            } catch (e: PackageManager.NameNotFoundException) {
//                                e.printStackTrace()
//                            }
//
//                        }




                        if (result != null) {



                            for (ads in result) {
                                if (ads.ads_type == 2){
                                    if (ads.cpm > maxCpm) {
                                        maxCpm = ads.cpm.toInt()
                                        maxCpmAdscode = ads.adscode

                                        SharedPrefs.setResponse(this@OpenActivity, maxCpmAdscode)
                                        val AD_UNIT_ID = SharedPrefs.getresponse(this@OpenActivity)
                                        // loadBanner(ads.ads_type)



                                        // showBannerAd()

                                        // loadAd()
                                    }
                                }


                            }





                        }

                    } catch (e: Exception) {
                        Log.d("dvbvb", e.toString())
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("dvbvb", e.toString())
            //hideLoader()
        }


    }

}
