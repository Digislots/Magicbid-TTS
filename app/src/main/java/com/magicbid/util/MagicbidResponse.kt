package com.magicbid.util

import com.ttsmagic.util.Adscode
import com.ttsmagic.util.Appdetails
import com.ttsmagic.util.Publisherid

data class MagicbidResponse(
    val adscode: List<Adscode>,
    val appdetails: Appdetails,
    val publisherid: List<Publisherid>
)