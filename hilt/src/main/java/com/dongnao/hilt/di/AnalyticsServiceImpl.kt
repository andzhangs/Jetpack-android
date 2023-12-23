package com.dongnao.hilt.di

import android.util.Log
import javax.inject.Inject

/**
 *
 * @author zhangshuai
 * @date 2023/12/23 16:38
 * @description 自定义类描述
 */
class AnalyticsServiceImpl @Inject constructor() : AnalyticsService {

    override fun analyticsMethods(info: String) {
        Log.i("print_logs", "analyticsMethods: $info")
    }
}