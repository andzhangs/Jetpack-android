package io.dushu.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * author: zhangshuai 6/26/21 8:37 PM
 * email: zhangshuai@dushu365.com
 * mark:
 */
class MyViewModel : ViewModel() {

    var index=0

    private val liveData: MutableLiveData<Int> =MutableLiveData<Int>()

    init {
        liveData.value = index
    }

    fun getCurrentSecond(): MutableLiveData<Int> {
        return liveData
    }

    fun add(){
        liveData.postValue(++index)
    }
}