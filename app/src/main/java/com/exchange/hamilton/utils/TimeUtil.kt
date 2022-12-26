package com.exchange.hamilton.utils

import org.joda.time.DateTime

class TimeUtil {
    fun getShortDate(epochTime:Long?):DateTime{
        return DateTime(epochTime)
    }
}