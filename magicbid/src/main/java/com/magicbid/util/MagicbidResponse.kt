package com.magicbid.util

data class MagicbidResponse(
    val adscode: List<Adscode>,
    val appdetails: Appdetails,
    val publisherid: List<Publisherid>
)